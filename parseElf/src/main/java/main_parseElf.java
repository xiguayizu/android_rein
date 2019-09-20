import dex.myUtil;

import java.io.*;

/*
 * 解析elf文件
 * */
public class main_parseElf {
    public static elfStruct.ElfType32 type32 = new elfStruct.ElfType32();

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
            elfStruct.shdr shdr1 = parseSectionHeader(des);
            type32.shdrList.add( shdr1 );
        }
    }
    // 将buffer 转为 SectionHeader( shdr ) 格式
    private static elfStruct.shdr parseSectionHeader(byte[] des) {
        elfStruct.shdr shdr1 = new elfStruct.shdr();
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
    private static elfStruct.phdr parseProgramHeader(byte[] des) {
        elfStruct.phdr phdr1 = new elfStruct.phdr();
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
