package dex;

import sun.plugin.com.Utils;

public class StringIdsItem {
    public int string_data_off;
    public static int getSize(){
        return 4;
    }
    @Override
    public String toString(){
        return myUtil.outWithHex( myUtil.int2Byte(string_data_off) );
    }

}
