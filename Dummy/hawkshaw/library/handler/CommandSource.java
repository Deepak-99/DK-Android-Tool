package com.hawkshaw.library.handler;

import Y1.K;
import d3.C0393a;

public enum CommandSource {
    Unknown,
    Pushy,
    ScheduledCommand,
    PushData,
    Socket;

    static {
        CommandSource[] $values;
        $ENTRIES = K.J($values);
    }

    public static C0393a getEntries() {
        return $ENTRIES;
    }
}
