package org.everest.dic.test;

import dic.AutoInject;

public class EmailMessage extends SendMessage{
    @AutoInject
    private Message message;
}
