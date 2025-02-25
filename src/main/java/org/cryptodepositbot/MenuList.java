package org.cryptodepositbot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static org.cryptodepositbot.Menu.createBtn;

public class MenuList {
    public static Menu.Keyboard showMenu(boolean isAdmin) {
        String header = "Меню";

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        keyboard.add(List.of(createBtn("\uD83D\uDD25 Пополнить баланс", "add_deposit")));
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
}
