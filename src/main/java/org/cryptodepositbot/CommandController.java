package org.cryptodepositbot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class CommandController {

    public static void execute(String command, UserModel userModel) {
        System.out.println("Command: "+command+"\tUser: " + userModel.getUsername());
        switch (command) {
            case "/start":
                CommandController.start(userModel);
                break;
            case "/show_menu":
                CommandController.showMenu(userModel);
                break;
            default:
                break;
        }
    }


    private static void start(UserModel userModel) {
        if (!UserService.isRegistered(userModel.getUserId())) {
            String welcomeMessage = "Привет, " + userModel.getFirstName() + ", добро пожаловать! \uD83E\uDD19";
            UserService.createUser(userModel.getUserId(), userModel.getUsername());
            CryptoDepositBot.send(new SendMessage(userModel.getUserId(), welcomeMessage));
            AdminController.sendMessageToAllAdmins("Новый пользователь @" + userModel.getUsername() + " присоединился");
        } else {
            String welcomeMessage = "Привет, " + userModel.getFirstName() + ", добро пожаловать снова! \uD83D\uDE09";
            CryptoDepositBot.send(new SendMessage(userModel.getUserId(), welcomeMessage));
        }
    }

    private static void showMenu(UserModel userModel) {
        boolean isAdmin = AdminService.isAdmin(userModel.getUserId());
        CryptoDepositBot.send(Menu.createInlineKeyboard(userModel.getUserId(), MenuList.showMenu(isAdmin)));
    }
}
