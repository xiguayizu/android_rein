package dex;

public class MapItem {
    public short type;
    public short unuse;
    public int size;
    public int offset;
    public static int getSize(){
        return 2+2+4+4;
    }
    @Override
    public String toString(){
        return "type:"+type+", unuse:"+unuse+", size:"+size+", offset:"+offset;
    }
}
