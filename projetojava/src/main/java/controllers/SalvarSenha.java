package controllers;

import org.mindrot.jbcrypt.BCrypt;

public class SalvarSenha {
    private static final int COST = 12;
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(COST));
    }
}

// import org.mindrot.jbcrypt.BCrypt;

// public class HashUtil {

//     // Define o custo do BCrypt (quanto maior, mais seguro, mas mais lento)
//     private static final int COST = 12;

//     // Método para gerar o hash da senha
//     public static String hashPassword(String password) {
//         return BCrypt.hashpw(password, BCrypt.gensalt(COST));
//     }

//     // Método para verificar se a senha corresponde ao hash armazenado
//     public static boolean checkPassword(String password, String hashedPassword) {
//         return BCrypt.checkpw(password, hashedPassword);
//     }

//     public static void main(String[] args) {
//         // Exemplo de uso
//         String senhaOriginal = "senha123";

//         // Gera o hash da senha
//         String hashSenha = hashPassword(senhaOriginal);
//         System.out.println("Senha original: " + senhaOriginal);
//         System.out.println("Hash da senha: " + hashSenha);

//         // Verifica se a senha corresponde ao hash armazenado
//         boolean senhaCorreta = checkPassword(senhaOriginal, hashSenha);
//         System.out.println("Senha correta? " + senhaCorreta);
//     }
// }
