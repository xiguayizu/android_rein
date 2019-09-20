package dex;

public class TypeIdsItem {
    /**
     * struct type_ids_item
     {
     uint descriptor_idx;
     }
     */

    public int descriptor_idx;

    public static int getSize(){
        return 4;
    }

    @Override
    public String toString(){
        return myUtil.outWithHex(myUtil.int2Byte(descriptor_idx));
    }
}
