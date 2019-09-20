package dex;

public class HeaderType {
    public byte[] magic = new byte[8];

    public int checksum; // alder32 以下的文件
    public byte[] siganature = new byte[20];  // sha-1 以下的文件

    public int file_size; // dex 文件大小
    public int header_size; // header 区域大小

    public int endian_tag;  // 大小端标签, 标准dex文件格式为小端, 此项一般固定为 0x12345678 常量
    public int link_size;   // *
    public int link_off;    // *- 这两个字段表示是链接数据的大小和偏移值
    public int map_off;     // ???
                            // 校验dex的类型存在
                            // android校验map_off不符合的话，就停止解析

    public int string_ids_size;
    public int string_ids_off;  // 字串
    public int type_ids_size;
    public int type_ids_off;    // 类型
    public int proto_ids_size;
    public int proto_ids_off;   // 方法
    public int field_ids_size;
    public int field_ids_off;   // 域
    public int method_ids_size;
    public int method_ids_off;  // 方法

    public int class_defs_size; //

    public int class_defs_off;  // 类
    public int data_size;       // 字节单位
    public int data_off;        // 数据
}
