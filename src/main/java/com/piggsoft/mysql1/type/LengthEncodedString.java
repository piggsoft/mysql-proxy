package com.piggsoft.mysql1.type;

import com.piggsoft.mysql1.packet.PacketByte;
import com.piggsoft.mysql1.tool.PacketByteTool;

public class LengthEncodedString {

    public static PacketByte[] resolveEncodedString(PacketByte[] array, int pos) {
        int index = pos;
        PacketByte[] result = null;
        PacketByte[] length = null;
        length = LengthEncodedInteger.resolveEncodedInteger(array, pos);
        index += length.length == 1 ? 1 : length.length + 1;
        int size = PacketByteTool.computeLength(length);
        result = PacketByteTool.subArray(array, index, size);
        return result;
    }
}
