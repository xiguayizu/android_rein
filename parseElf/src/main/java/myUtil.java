import java.io.*;
import java.util.Arrays;

public class myUtil {
    /**
     * the traditional io way
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] getFileByteArray(String filename) throws IOException {

        File f = new File(filename);
        if (!f.exists()) {
            throw new FileNotFoundException(filename);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }

    public static byte[] copyBytes(byte[] header, int start, int end)
    {
        return Arrays.copyOfRange(header, start, start+end);
    }

    private static final char[] HEX_CHAR = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    public static String outWithHex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        int num;
        for(byte b : data){
            num = b < 0 ? 256+b : b;
            sb.append(" ").append(HEX_CHAR[num/16]).append(HEX_CHAR[num%16]);
        }
//        System.out.println(sb.toString());
        return sb.toString();
    }

    public static int byte2Short(byte[] b) {
        // outWithHex(b);
        int ret = b[1];
        ret += b[0];
        return ret;
    }

    public static int byte2Int(byte[] res) {
        int targets = (res[0] & 0xff)
                | ((res[1] << 8) & 0xff00)
                | ((res[2] << 24) >>> 8)
                | (res[3] << 24);
        return targets;
    }
}
