package com.links.login;

import com.links.login.exceptions.*;

public class LoginService {
    private final UserStorage userStorage;

    private static final String UNF = "User non trovato";

    public LoginService() {
        userStorage = new UserStorage();
    }

    public String getUserList() {
        return userStorage.toString();
    }

    public String createUser(RegisterUser registerUser) throws EmptyUsernameException, PasswordDoNotMatchException, PasswordNotValidException, UserAlreadyExistsException {
        User user = registerUser.getUser();
        if (user == null || user.getUsername() == null) {
            Logger.log("Creazione di un nuovo utente fallita, username non valido o vuoto");
            throw new EmptyUsernameException("Username vuoto o non valido");
        }
        if (!registerUser.passwordsMatch()) {
            Logger.log("User " + user.getId() + " non è riuscito a creare un nuovo utente, le password non corrispondono");
            throw new PasswordDoNotMatchException("Le password non corrispondono");
        }
        if (!user.setPassword(user.getPassword())) {
            Logger.log("User " + user.getId() + " non è riuscito a creare un nuovo utente, password non valida");
            throw new PasswordNotValidException("Password non valida");
        }
        String ret = "User created: " + user;
        if (!userStorage.addUser(user)) {
            Logger.log("User " + user.getId() + " non è riuscito a creare un nuovo utente, user già esistente");
            throw new UserAlreadyExistsException("User già esistente");
        }
        Logger.log(ret);
        return ret;
    }

    public String updateUser(ChangeUsername changeUsername) throws LoginException, EmptyUsernameException, UserAlreadyExistsException {
        User user = changeUsername.user();
        try {
            login(user);
        } catch (LoginException e) {
            throw new LoginException("Login fallito");
        }
        String newUsername = changeUsername.newUsername();
        if (newUsername == null) {
            Logger.log("User " + user.getId() + " ha fallito a cambiare lo username, username vuoto");
            throw new EmptyUsernameException("Username vuotoU");
        }
        if (userStorage.containsUser(newUsername)) {
            Logger.log("User " + user.getId() + " ha fallito a cambiare lo username in " + newUsername);
            throw new UserAlreadyExistsException("Utente già esistente");
        }
        userStorage.updateUser(user, newUsername);
        Logger.log("User " + user.getId() + " ha cambiato lo username in " + newUsername);
        return "Username aggiornato con successo in " + newUsername;
    }

    public String deleteUser(long id) throws UserNotFoundException {
        if (!userStorage.removeUser(id)) {
            Logger.log("Tentative di rimozione di un utente non esistente: " + id);
            throw new UserNotFoundException(UNF);
        }
        String ret = "User eliminato: " + id;
        Logger.log(ret);
        return ret;
    }

    public String login(User user) throws WrongPasswordException, UserNotFoundException {
        if (user == null) {
            throw new UserNotFoundException(UNF);
        }
        if (!userStorage.containsUser(user.getId())) {
            Logger.log("User" + user.getId() + " ha provato a fare il login, user non trovato");
            throw new UserNotFoundException(UNF);
        }
        User user1 = userStorage.getUser(user.getId());
        if (!user1.passwordMatch(user)) {
            Logger.log("User" + user.getId() + " ha provato a fare il login, password errata");
            throw new WrongPasswordException("Password errata");
        }
        Logger.log("User" + user.getId() + "ha effettuato il login");
        return "Login effettuato con successo";
    }

    public String resetPassword(PasswordReset passwordReset) throws UserNotFoundException, PasswordDoNotMatchException {
        if (!passwordReset.passwordsMatch()) {
            throw new PasswordDoNotMatchException("Le password non corrispondono");
        }
        User user = userStorage.getUser(passwordReset.getUsername());
        if (user == null || !userStorage.containsUser(user.getId())) {
            throw new UserNotFoundException(UNF);
        }
        user.setPassword(passwordReset.getNewPassword());
        userStorage.writeToJson();
        return "Password aggiornata con successo";
    }
}
