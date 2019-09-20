import dex.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
* 解析dex文件
* */
public class main_parseDex {
//    public static dexStruct type32 = new dexStruct();

    public static void main(String[] args){
        try {
            parseDex();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseDex() throws IOException {
        byte[] fileBytes = myUtil.getFileByteArray("dex/construct_dex_widthRun.dex");

        praseDexHeader(fileBytes);
        parseStringIds(fileBytes);

        System.out.println("Parse StringList:");
        parseStringList(fileBytes);
        System.out.println("++++++++++++++++++++++++++++++++++++++++");

        System.out.println("Parse TypeIds:");
        parseTypeIds(fileBytes);
        System.out.println("++++++++++++++++++++++++++++++++++++++++");

        System.out.println("Parse ProtoIds:");
        parseProtoIds(fileBytes);
        System.out.println("++++++++++++++++++++++++++++++++++++++++");

    }



    private static int stringIdOffset = 0;
    private static int stringIdsSize = 0;
    private static int stringIdsOffset = 0;
    private static int typeIdsSize = 0;
    private static int typeIdsOffset = 0;
    private static int protoIdsSize = 0;
    private static int protoIdsOffset = 0;
    private static int fieldIdsSize = 0;
    private static int fieldIdsOffset = 0;
    private static int methodIdsSize = 0;
    private static int methodIdsOffset = 0;
    private static int classIdsSize = 0;
    private static int classIdsOffset = 0;
    private static int dataIdsSize = 0;
    private static int dataIdsOffset = 0;

    private static int mapListOffset = 0;

    private static List<StringIdsItem> stringIdsList = new ArrayList<StringIdsItem>();
    private static List<String> stringList = new ArrayList<String>();
    private static List<TypeIdsItem> typeIdsList = new ArrayList<TypeIdsItem>();
    private static List<ProtoIdsItem> protoIdsList = new ArrayList<ProtoIdsItem>();



    private static HeaderType praseDexHeader(byte[] fileBytes) {
        HeaderType headerType = new HeaderType();
        // 解析魔数
        byte[] magic = myUtil.copyBytes(fileBytes, 0, 8);
        headerType.magic = magic;

        // 解析checksum
        byte[] checksumByte = myUtil.copyBytes(fileBytes, 0, 8);
        headerType.checksum = myUtil.byte2Int(checksumByte);


        //解析siganature
        byte[] siganature = myUtil.copyBytes(fileBytes, 12, 20);
        headerType.siganature = siganature;

        //解析file_size
        byte[] fileSizeByte = myUtil.copyBytes(fileBytes, 32, 4);
        headerType.file_size = myUtil.byte2Int(fileSizeByte);

        //解析header_size
        byte[] headerSizeByte = myUtil.copyBytes(fileBytes, 36, 4);
        headerType.header_size = myUtil.byte2Int(headerSizeByte);

        //解析endian_tag
        byte[] endianTagByte = myUtil.copyBytes(fileBytes, 40, 4);
        headerType.endian_tag = myUtil.byte2Int(endianTagByte);

        //解析link_size
        byte[] linkSizeByte = myUtil.copyBytes(fileBytes, 44, 4);
        headerType.link_size = myUtil.byte2Int(linkSizeByte);

        //解析link_off
        byte[] linkOffByte = myUtil.copyBytes(fileBytes, 48, 4);
        headerType.link_off = myUtil.byte2Int(linkOffByte);

        //解析map_off
        byte[] mapOffByte = myUtil.copyBytes(fileBytes, 52, 4);
        headerType.map_off = myUtil.byte2Int(mapOffByte);

        //解析string_ids_size
        byte[] stringIdsSizeByte = myUtil.copyBytes(fileBytes, 56, 4);
        headerType.string_ids_size = myUtil.byte2Int(stringIdsSizeByte);

        //解析string_ids_off
        byte[] stringIdsOffByte = myUtil.copyBytes(fileBytes, 60, 4);
        headerType.string_ids_off = myUtil.byte2Int(stringIdsOffByte);

        //解析type_ids_size
        byte[] typeIdsSizeByte = myUtil.copyBytes(fileBytes, 64, 4);
        headerType.type_ids_size = myUtil.byte2Int(typeIdsSizeByte);

        //解析type_ids_off
        byte[] typeIdsOffByte = myUtil.copyBytes(fileBytes, 68, 4);
        headerType.type_ids_off = myUtil.byte2Int(typeIdsOffByte);

        //解析proto_ids_size
        byte[] protoIdsSizeByte = myUtil.copyBytes(fileBytes, 72, 4);
        headerType.proto_ids_size = myUtil.byte2Int(protoIdsSizeByte);

        //解析proto_ids_off
        byte[] protoIdsOffByte = myUtil.copyBytes(fileBytes, 76, 4);
        headerType.proto_ids_off = myUtil.byte2Int(protoIdsOffByte);

        //解析field_ids_size
        byte[] fieldIdsSizeByte = myUtil.copyBytes(fileBytes, 80, 4);
        headerType.field_ids_size = myUtil.byte2Int(fieldIdsSizeByte);

        //解析field_ids_off
        byte[] fieldIdsOffByte = myUtil.copyBytes(fileBytes, 84, 4);
        headerType.field_ids_off = myUtil.byte2Int(fieldIdsOffByte);

        //解析method_ids_size
        byte[] methodIdsSizeByte = myUtil.copyBytes(fileBytes, 88, 4);
        headerType.method_ids_size = myUtil.byte2Int(methodIdsSizeByte);

        //解析method_ids_off
        byte[] methodIdsOffByte = myUtil.copyBytes(fileBytes, 92, 4);
        headerType.method_ids_off = myUtil.byte2Int(methodIdsOffByte);

        //解析class_defs_size
        byte[] classDefsSizeByte = myUtil.copyBytes(fileBytes, 96, 4);
        headerType.class_defs_size = myUtil.byte2Int(classDefsSizeByte);

        //解析class_defs_off
        byte[] classDefsOffByte = myUtil.copyBytes(fileBytes, 100, 4);
        headerType.class_defs_off = myUtil.byte2Int(classDefsOffByte);

        //解析data_size
        byte[] dataSizeByte = myUtil.copyBytes(fileBytes, 104, 4);
        headerType.data_size = myUtil.byte2Int(dataSizeByte);

        //解析data_off
        byte[] dataOffByte = myUtil.copyBytes(fileBytes, 108, 4);
        headerType.data_off = myUtil.byte2Int(dataOffByte);



        System.out.println("header:"+headerType);

        stringIdOffset = headerType.header_size;//header之后就是string ids

        stringIdsSize = headerType.string_ids_size;
        stringIdsOffset = headerType.string_ids_off;
        typeIdsSize = headerType.type_ids_size;
        typeIdsOffset = headerType.type_ids_off;
        fieldIdsSize = headerType.field_ids_size;
        fieldIdsOffset = headerType.field_ids_off;
        protoIdsSize = headerType.proto_ids_size;
        protoIdsOffset = headerType.proto_ids_off;
        methodIdsSize = headerType.method_ids_size;
        methodIdsOffset = headerType.method_ids_off;
        classIdsSize = headerType.class_defs_size;
        classIdsOffset = headerType.class_defs_off;

        mapListOffset = headerType.map_off;

        return headerType;
    }

    /************************解析字符串********************************/
    /* 根据 stringIdsOffset+i*idSize 索引即可 */
    public static void parseStringIds(byte[] srcByte){
        int idSize = StringIdsItem.getSize();
        int countIds = stringIdsSize;
        for( int i=0;i<countIds;i++ ){
            stringIdsList.add(parseStringIdsItem(myUtil.copyBytes(srcByte, stringIdsOffset+i*idSize, idSize)));
        }
    }
    public static void parseStringList(byte[] srcByte){
        //第一个字节还是字符串的长度
        for(StringIdsItem item : stringIdsList){
            String str = getString(srcByte, item.string_data_off);
            System.out.println("str:"+str);
            stringList.add(str);
        }
    }

    /***************************解析类型******************************/
    public static void parseTypeIds(byte[] srcByte){
        int idSize = TypeIdsItem.getSize();
        int countIds = typeIdsSize;
        for(int i=0;i<countIds;i++){
            typeIdsList.add(parseTypeIdsItem(myUtil.copyBytes(srcByte, typeIdsOffset+i*idSize, idSize)));
        }

        //这里的descriptor_idx就是解析之后的字符串中的索引值
        for(TypeIdsItem item : typeIdsList){
            System.out.println("typeStr:"+stringList.get(item.descriptor_idx));
        }
    }

    /***************************解析Proto***************************/
    public static void parseProtoIds(byte[] srcByte){
        int idSize = ProtoIdsItem.getSize();
        int countIds = protoIdsSize;
        for(int i=0;i<countIds;i++){
            protoIdsList.add(parseProtoIdsItem(myUtil.copyBytes(srcByte, protoIdsOffset+i*idSize, idSize)));
        }

        for(ProtoIdsItem item : protoIdsList){
            System.out.println("proto:"+stringList.get(item.shorty_idx)+","+stringList.get(item.return_type_idx));
            //有的方法没有参数，这个值就是0
            if(item.parameters_off != 0){
                item = parseParameterTypeList(srcByte, item.parameters_off, item);
            }
        }
    }
    //解析方法的所有参数类型
    private static ProtoIdsItem parseParameterTypeList(byte[] srcByte, int startOff, ProtoIdsItem item){
        //解析size和size大小的List中的内容
        byte[] sizeByte = myUtil.copyBytes(srcByte, startOff, 4);
        int size = myUtil.byte2Int(sizeByte);
        List<String> parametersList = new ArrayList<String>();
        List<Short> typeList = new ArrayList<Short>(size);
        for(int i=0;i<size;i++){
            byte[] typeByte = myUtil.copyBytes(srcByte, startOff+4+2*i, 2);
            typeList.add(myUtil.byte2Short(typeByte));
        }
        System.out.println("param count:"+size);
        for(int i=0;i<typeList.size();i++){
            System.out.println("type:"+stringList.get(typeList.get(i)));
            int index = typeIdsList.get(typeList.get(i)).descriptor_idx;
            parametersList.add(stringList.get(index));
        }

        item.parameterCount = size;
        item.parametersList = parametersList;

        return item;
    }







    private static ProtoIdsItem parseProtoIdsItem(byte[] srcByte){
        ProtoIdsItem item = new ProtoIdsItem();
        byte[] shortyIdxByte = myUtil.copyBytes(srcByte, 0, 4);
        item.shorty_idx = myUtil.byte2Int(shortyIdxByte);
        byte[] returnTypeIdxByte = myUtil.copyBytes(srcByte, 4, 8);
        item.return_type_idx = myUtil.byte2Int(returnTypeIdxByte);
        byte[] parametersOffByte = myUtil.copyBytes(srcByte, 8, 4);
        item.parameters_off = myUtil.byte2Int(parametersOffByte);
        return item;
    }
    private static StringIdsItem parseStringIdsItem(byte[] srcByte){
        StringIdsItem item = new StringIdsItem();
        byte[] idsByte = myUtil.copyBytes(srcByte, 0, 4);
        item.string_data_off = myUtil.byte2Int(idsByte);
        return item;
    }
    private static TypeIdsItem parseTypeIdsItem(byte[] srcByte){
        TypeIdsItem item = new TypeIdsItem();
        byte[] descriptorIdxByte = myUtil.copyBytes(srcByte, 0, 4);
        item.descriptor_idx = myUtil.byte2Int(descriptorIdxByte);
        return item;
    }

    /**
     * 这里是解析一个字符串
     * 有两种方式
     * 1、第一个字节就是字符串的长度
     * 2、每个字符串的结束符是00
     * @param srcByte
     * @param startOff
     * @return
     *
     * [长度] [字符串]
     *
     */
    private static String getString(byte[] srcByte, int startOff){

        //第一种方式
        byte size = srcByte[startOff];  // 取长度
        byte[] strByte = myUtil.copyBytes(srcByte, startOff+1, size);  // 取字符串
        String result = "";
        try{
            result = new String(strByte, "UTF-8");
        }catch(Exception e){
        }
        return result;

        //第二种方式
		/*System.out.println("off:"+Utils.bytesToHexString(Utils.int2Byte(startOff)));
		List<Byte> byteList = new ArrayList<Byte>();
		//第一个字节是长度，所以过滤一下
		byte b = srcByte[startOff+1];
		int index = 0;
		while(b != 0){
			byteList.add(b);
			index++;
			b = srcByte[startOff+index];
		}
		byte[] byteAry = new byte[byteList.size()+1];
		for(int i=0;i<byteList.size();i++){
			byteAry[i] = byteList.get(i);
		}
		byteAry[byteList.size()] = 0;
		String result = "";
		try{
			result = new String(byteAry, "UTF-8");
		}catch(Exception e){
		}
		return result;*/

    }
}
