import com.sun.xml.internal.ws.api.message.Header;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class parseElf {
    public static int EI_NIDENT = 16;
    public static int Elf32_Half = 2;
    public static int Elf32_Word = 4;
    public static int Elf32_Addr = 8;
    public static int Elf32_Off = 8;

    static public class hdr {
        byte[] e_ident = new byte[EI_NIDENT];
        byte[] e_type = new byte[Elf32_Half];
        byte[] e_machine = new byte[Elf32_Half];
        byte[] e_version = new byte[Elf32_Word];
        byte[] e_entry = new byte[Elf32_Addr];
        byte[] e_phoff = new byte[Elf32_Off];       // 程序头
        byte[] e_shoff = new byte[Elf32_Off];       // 段头
        byte[] e_flags = new byte[Elf32_Word];
        byte[] e_ehsize = new byte[Elf32_Half];
        byte[] e_phentsize = new byte[Elf32_Half];
        byte[] e_phnum = new byte[Elf32_Half];
        byte[] e_shentsize = new byte[Elf32_Half];
        byte[] e_shnum = new byte[Elf32_Half];
        byte[] e_shstrndx = new byte[Elf32_Half];   // 是String 段在整个段列表中的索引值，用于后面定位String段的位置
        @Override
        public String toString(){
            return "magic:" + myUtil.outWithHex(e_ident)
                  + "\ne_type:" + myUtil.outWithHex(e_type)
                  + "\ne_machine:" + myUtil.outWithHex(e_machine)
                  + "\ne_version:" + myUtil.outWithHex(e_version)
                  + "\ne_entry:" + myUtil.outWithHex(e_entry)
                  + "\ne_phoff:" + myUtil.outWithHex(e_phoff)
                  + "\ne_shoff:" + myUtil.outWithHex(e_shoff)
                  + "\ne_flags:" + myUtil.outWithHex(e_flags)
                  + "\ne_ehsize:" + myUtil.outWithHex(e_ehsize)
                  + "\ne_phentsize:" + myUtil.outWithHex(e_phentsize)
                  + "\ne_phnum:" + myUtil.outWithHex(e_phnum)
                  + "\ne_shentsize:" + myUtil.outWithHex(e_shentsize)
                  + "\ne_shnum:" + myUtil.outWithHex(e_shnum)
                  + "\ne_shstrndx:" + myUtil.outWithHex(e_shstrndx);
        }
    };
    // 程序头
    static public class phdr {
        byte[] p_type = new byte[Elf32_Word];
        byte[] p_offset = new byte[Elf32_Off];
        byte[] p_vaddr = new byte[Elf32_Addr];
        byte[] p_paddr = new byte[Elf32_Addr];
        byte[] p_filesz = new byte[Elf32_Word];
        byte[] p_memsz = new byte[Elf32_Word];
        byte[] p_flags = new byte[Elf32_Word];
        byte[] p_align = new byte[Elf32_Word];
        @Override
        public String toString(){
            return "p_type:"+ myUtil.outWithHex(p_type)
                    +"\np_offset:"+myUtil.outWithHex(p_offset)
                    +"\np_vaddr:"+myUtil.outWithHex(p_vaddr)
                    +"\np_paddr:"+myUtil.outWithHex(p_paddr)
                    +"\np_filesz:"+myUtil.outWithHex(p_filesz)
                    +"\np_memsz:"+myUtil.outWithHex(p_memsz)
                    +"\np_flags:"+myUtil.outWithHex(p_flags)
                    +"\np_align:"+myUtil.outWithHex(p_align);
        }
    };
    // 段头
    static public class shdr {
        byte[] sh_name = new byte[Elf32_Word];
        byte[] sh_type = new byte[Elf32_Word];
        byte[] sh_flags = new byte[Elf32_Word];
        byte[] sh_addr = new byte[Elf32_Addr];
        byte[] sh_offset = new byte[Elf32_Off];
        byte[] sh_size = new byte[Elf32_Word];
        byte[] sh_link = new byte[Elf32_Word];
        byte[] sh_info = new byte[Elf32_Word];
        byte[] sh_addralign = new byte[Elf32_Word];
        byte[] sh_entsize = new byte[Elf32_Word];
        @Override
        public String toString(){
            return   "sh_name:"+myUtil.outWithHex(sh_name)/*Utils.byte2Int(sh_name)*/
                    +"\nsh_type:"+myUtil.outWithHex(sh_type)
                    +"\nsh_flags:"+myUtil.outWithHex(sh_flags)
                    +"\nsh_add:"+myUtil.outWithHex(sh_addr)
                    +"\nsh_offset:"+myUtil.outWithHex(sh_offset)
                    +"\nsh_size:"+myUtil.outWithHex(sh_size)
                    +"\nsh_link:"+myUtil.outWithHex(sh_link)
                    +"\nsh_info:"+myUtil.outWithHex(sh_info)
                    +"\nsh_addralign:"+myUtil.outWithHex(sh_addralign)
                    +"\nsh_entsize:"+ myUtil.outWithHex(sh_entsize);
        }
    };
    static public class ElfType32{
        hdr hdr = new hdr();     // 文件头

        List<phdr> phdrList = new ArrayList<phdr>();         // 程序头
        public void printPhdrList(){
            for(int i=0;i<phdrList.size();i++){
                System.out.println();
                System.out.println("The "+(i+1)+" Program Header:");
                System.out.println(phdrList.get(i).toString());
            }
        }

        List<shdr> shdrList = new ArrayList<shdr>();         // selection 列表
        public void printShdrList(){
            for( int i=0;i<shdrList.size();i++ ){
                System.out.println();
                System.out.println("The "+(i+1)+" Section Header:");
                System.out.println(shdrList.get(i));
            }
        }
    }
    public static ElfType32 type32 = new ElfType32();




    public static void main(String[] args){
        try {
            parseSo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseSo() throws IOException {
        // 读取文件为字节流
        byte[] fileBytes = myUtil.getFileByteArray("so/libJNIDemo-lib.so");

        // 读取头部内容
        System.out.println("+++++++++++++++++++Elf Header+++++++++++++++++");
        parseHeader(fileBytes, 0);
        System.out.println("header:\n" + type32.hdr);


        // 读取程序头信息
        System.out.println();
        System.out.println("+++++++++++++++++++Program Header+++++++++++++++++");
        int p_header_offset = myUtil.byte2Int(type32.hdr.e_phoff);
        parseProgramHeaderList(fileBytes, p_header_offset);
        type32.printPhdrList();


        // 读取段头信息
        System.out.println();
        System.out.println("+++++++++++++++++++Section Header++++++++++++++++++");
        int s_header_offset = myUtil.byte2Short(type32.hdr.e_shoff);
        parseSectionHeaderList(fileBytes, s_header_offset);
        type32.printShdrList();


    }


    public static void parseHeader(byte[] header, int offset)
    {
        if( header == null )
        {
            System.out.println("header is null");
            return ;
        }
        type32.hdr.e_ident = myUtil.copyBytes(header, 0, 16);
        type32.hdr.e_type = myUtil.copyBytes(header, 16, 2);
        type32.hdr.e_machine = myUtil.copyBytes(header, 19, 2);
        type32.hdr.e_version = myUtil.copyBytes(header, 20, 4);
        type32.hdr.e_entry = myUtil.copyBytes(header, 24, 4);
        type32.hdr.e_phoff = myUtil.copyBytes(header, 28, 4);
        type32.hdr.e_shoff = myUtil.copyBytes(header, 32, 4);
        type32.hdr.e_flags = myUtil.copyBytes(header, 36, 4);
        type32.hdr.e_ehsize = myUtil.copyBytes(header, 40, 2);
        type32.hdr.e_phentsize = myUtil.copyBytes(header, 42, 2);
        type32.hdr.e_phnum = myUtil.copyBytes(header, 44, 2);
        type32.hdr.e_shentsize = myUtil.copyBytes(header, 46, 2);
        type32.hdr.e_shnum = myUtil.copyBytes(header, 48, 2);
        type32.hdr.e_shstrndx = myUtil.copyBytes(header, 50, 2);
    }

    // 填充 SectionHeader List 格式
    public static void parseSectionHeaderList(byte[] header, int offset){
        int header_size = 40; // 40个字节
        int header_count = myUtil.byte2Short(type32.hdr.e_shnum);  // 头部的个数
        byte[] des = new byte[header_size];
        for ( int i=0;i<header_count;i++ ){
            System.arraycopy(header, i*header_size + offset, des, 0, header_size);
            shdr shdr1 = parseSectionHeader(des);
            type32.shdrList.add( shdr1 );
        }
    }
    // 将buffer 转为 SectionHeader( shdr ) 格式
    private static shdr parseSectionHeader(byte[] des) {
        shdr shdr1 = new shdr();
        shdr1.sh_name = myUtil.copyBytes(des, 0, 4);
        shdr1.sh_type = myUtil.copyBytes(des, 4, 4);
        shdr1.sh_flags = myUtil.copyBytes(des, 8, 4);
        shdr1.sh_addr = myUtil.copyBytes(des, 12, 4);
        shdr1.sh_offset = myUtil.copyBytes(des, 16, 4);
        shdr1.sh_size = myUtil.copyBytes(des, 20, 4);
        shdr1.sh_link = myUtil.copyBytes(des, 24, 4);
        shdr1.sh_info = myUtil.copyBytes(des, 28, 4);
        shdr1.sh_addralign = myUtil.copyBytes(des, 32, 4);
        shdr1.sh_entsize = myUtil.copyBytes(des, 36, 4);
        return shdr1;
    }
    // 解析程序头信息
    // @param header
    private static void parseProgramHeaderList(byte[] header, int offset) {
        int header_size = 32; // 32个字节
        int header_count = myUtil.byte2Short(type32.hdr.e_phnum); // 头部的个数
        byte[] des = new byte[header_size];
        for( int i=0;i<header_count;i++ ){
            System.arraycopy(header, i*header_size + offset, des, 0, header_size);
            type32.phdrList.add( parseProgramHeader(des) );
        }
    }
    // 解析程序头
    private static phdr parseProgramHeader(byte[] des) {
        phdr phdr1 = new phdr();
        phdr1.p_type = myUtil.copyBytes(des, 0, 4);
        phdr1.p_offset = myUtil.copyBytes(des, 4, 4);
        phdr1.p_vaddr = myUtil.copyBytes(des, 8, 4);
        phdr1.p_paddr = myUtil.copyBytes(des, 12, 4);
        phdr1.p_filesz = myUtil.copyBytes(des, 16, 4);
        phdr1.p_memsz = myUtil.copyBytes(des, 20, 4);
        phdr1.p_flags = myUtil.copyBytes(des, 24, 4);
        phdr1.p_align = myUtil.copyBytes(des, 28, 4);
        return phdr1;
    }
}
