package com.banking.utils;

import org.fusesource.jansi.AnsiConsole;
import org.fusesource.jansi.Ansi;

public final class ConsoleClearUtility {
    private ConsoleClearUtility() {
    }

    public static void clear() {
        AnsiConsole.out().print(Ansi.ansi()
                .eraseScreen()
                .cursor(0, 0));
        AnsiConsole.out().flush();
    }
}
