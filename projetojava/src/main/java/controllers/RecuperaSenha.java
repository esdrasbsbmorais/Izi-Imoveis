package controllers;

import org.mindrot.jbcrypt.BCrypt;

    public class RecuperaSenha {
        public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
