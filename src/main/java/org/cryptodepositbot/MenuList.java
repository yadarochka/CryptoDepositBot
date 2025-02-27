package org.cryptodepositbot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static org.cryptodepositbot.Menu.createBtn;

public class MenuList {
    public static Menu.Keyboard showMenu(boolean isAdmin) {
        String header = "Меню";

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        keyboard.add(List.of(createBtn("\uD83D\uDD25 Пополнить баланс", "show_deposit_introduction")));
        keyboard.add(List.of(createBtn("\uD83E\uDD35 Профиль", "show_profile"), createBtn("ℹ Помощь", "get_help")));
        keyboard.add(List.of(createBtn("\uD83D\uDCB5 Вывод средств", "cash_out")));
        if (isAdmin) {
            keyboard.add(List.of(createBtn("\uD83D\uDC51 Панель администратора", "show_admin_panel")));
        }

        return new Menu.Keyboard(header, keyboard);
    }
    public static Menu.Keyboard showAdminPanel() {
        String header = "Администратор";
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        keyboard.add(List.of(createBtn("\uD83D\uDC6C Показать всех пользователей", "show_all_users")));
        keyboard.add(List.of(createBtn("↩ Назад", "show_menu")));

        return new Menu.Keyboard(header, keyboard);
    }

    public static Menu.Keyboard notifyNewDeposit(String username) {
        String header = "Пользователь " + username + " пополнил депозит! ";
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        keyboard.add(List.of(createBtn("✅ Подтвердить", "accept_deposit")));
        keyboard.add(List.of(createBtn("✉ Написать пользователю", "contact_user")));

        return new Menu.Keyboard(header, keyboard);
    }

    public static Menu.Keyboard showDepositIntroduction() {
        String header = "Ну тут типа правила будут ок да.\n1. Не курите.\n2. Не шабите.\n3. Слушайте маму.";
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        keyboard.add(List.of(createBtn("✅ Я прочитал и согласен с условиями", "confirm_rules")));
        keyboard.add(List.of(createBtn("↩ Назад", "show_menu")));

        return new Menu.Keyboard(header, keyboard);
    }

    public static Menu.Keyboard addDeposit() {
        String header = "Отправь бабки на альфу +79173617745 А.Д.Ю.";
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        keyboard.add(List.of(createBtn("✅ Отправил", "add_deposit")));
        keyboard.add(List.of(createBtn("↩ Назад", "show_menu")));

        return new Menu.Keyboard(header, keyboard);
    }

}
