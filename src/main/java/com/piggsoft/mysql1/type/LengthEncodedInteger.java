package com.piggsoft.mysql1.type;

import com.piggsoft.mysql1.packet.PacketByte;
import com.piggsoft.mysql1.tool.PacketByteTool;

public class LengthEncodedInteger {

    public static PacketByte[] resolveEncodedInteger(PacketByte[] array, int pos) {
        int index = pos;
        PacketByte[] result = null;
        if (PacketByteTool.lengthInt(array[index]) == 1) {
            result = new PacketByte[1];
            result[0] = array[index];
        } else {
            int length = PacketByteTool.lengthInt(array[index++]);
            result = PacketByteTool.subArray(array, index, length);
        }
        return result;
    }

}
