import dex.myUtil;

import java.util.ArrayList;
import java.util.List;

public class elfStruct {
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
                    +"\np_offset:"+ myUtil.outWithHex(p_offset)
                    +"\np_vaddr:"+ myUtil.outWithHex(p_vaddr)
                    +"\np_paddr:"+ myUtil.outWithHex(p_paddr)
                    +"\np_filesz:"+ myUtil.outWithHex(p_filesz)
                    +"\np_memsz:"+ myUtil.outWithHex(p_memsz)
                    +"\np_flags:"+ myUtil.outWithHex(p_flags)
                    +"\np_align:"+ myUtil.outWithHex(p_align);
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
            return   "sh_name:"+ myUtil.outWithHex(sh_name)/*Utils.byte2Int(sh_name)*/
                    +"\nsh_type:"+ myUtil.outWithHex(sh_type)
                    +"\nsh_flags:"+ myUtil.outWithHex(sh_flags)
                    +"\nsh_add:"+ myUtil.outWithHex(sh_addr)
                    +"\nsh_offset:"+ myUtil.outWithHex(sh_offset)
                    +"\nsh_size:"+ myUtil.outWithHex(sh_size)
                    +"\nsh_link:"+ myUtil.outWithHex(sh_link)
                    +"\nsh_info:"+ myUtil.outWithHex(sh_info)
                    +"\nsh_addralign:"+ myUtil.outWithHex(sh_addralign)
                    +"\nsh_entsize:"+ myUtil.outWithHex(sh_entsize);
        }
    };
    static public class ElfType32{
        hdr hdr = new hdr();     // 文件头 readelf -h xxx

        List<phdr> phdrList = new ArrayList<phdr>();         // 程序头 列表    readelf -l xxx
        public void printPhdrList(){
            for(int i=0;i<phdrList.size();i++){
                System.out.println();
                System.out.println("The "+(i+1)+" Program Header:");
                System.out.println(phdrList.get(i).toString());
            }
        }

        List<shdr> shdrList = new ArrayList<shdr>();         // selection 列表 readelf -S xxx
        public void printShdrList(){
            for( int i=0;i<shdrList.size();i++ ){
                System.out.println();
                System.out.println("The "+(i+1)+" Section Header:");
                System.out.println(shdrList.get(i));
            }
        }
    }
}
