package com.yunforge.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Base64Encoder {
	public static final int BYTES_PER_ATOM = 3;
	public static final int BYTES_PER_LINE = 57;
	private static final char pad = 61;
	private static byte c2b[] = null;
	private static final char mapTable[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G',
			'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
			'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', '+', '/' };

	public static void encode(InputStream inputstream, OutputStream outputstream)
			throws IOException {
		byte abyte0[] = System.getProperty("line.separator").getBytes();
		int i = 0;
		byte abyte1[] = new byte[57];
		while ((i = inputstream.read(abyte1, 0, 57)) != -1) {
			for (int j = 0; j < i; j += 3) {
				if (j + 3 <= i) {
					encodeAtom(outputstream, abyte1, j, 3);
				} else {
					encodeAtom(outputstream, abyte1, j, i - j);
				}
			}
			outputstream.write(abyte0);
		}
	}

	public void encode(byte abyte0[], OutputStream outputstream)
			throws IOException {
		ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(
				abyte0);
		encode(((bytearrayinputstream)), outputstream);
	}

	public static String encode(String toEncode) throws IOException {
		byte bToEncode[] = null;
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		DataOutputStream dout = new DataOutputStream(bo);
		dout.writeBytes(toEncode);
		dout.flush();
		bToEncode = bo.toByteArray();
		bo.close();
		dout.close();
		return encode(bToEncode);
	}

	public static String encode(byte abyte0[]) throws IOException {
		ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
		ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(
				abyte0);
		encode(((bytearrayinputstream)), ((bytearrayoutputstream)));
		return bytearrayoutputstream.toString();
	}

	protected static void encodeAtom(OutputStream outputstream, byte abyte0[],
			int i, int j) throws IOException {
		if (j == 1) {
			byte byte0 = abyte0[i];
			int k = 0;
			boolean flag = false;
			outputstream.write(mapTable[byte0 >>> 2 & 0x3f]);
			outputstream.write(mapTable[(byte0 << 4 & 0x30) + (k >>> 4 & 0xf)]);
			outputstream.write(61);
			outputstream.write(61);
		} else if (j == 2) {
			byte byte1 = abyte0[i];
			byte byte3 = abyte0[i + 1];
			int l = 0;
			outputstream.write(mapTable[byte1 >>> 2 & 0x3f]);
			outputstream.write(mapTable[(byte1 << 4 & 0x30)
					+ (byte3 >>> 4 & 0xf)]);
			outputstream.write(mapTable[(byte3 << 2 & 0x3c) + (l >>> 6 & 3)]);
			outputstream.write(61);
		} else {
			byte byte2 = abyte0[i];
			byte byte4 = abyte0[i + 1];
			byte byte5 = abyte0[i + 2];
			outputstream.write(mapTable[byte2 >>> 2 & 0x3f]);
			outputstream.write(mapTable[(byte2 << 4 & 0x30)
					+ (byte4 >>> 4 & 0xf)]);
			outputstream
					.write(mapTable[(byte4 << 2 & 0x3c) + (byte5 >>> 6 & 3)]);
			outputstream.write(mapTable[byte5 & 0x3f]);
		}
	}

	public static String decode(String es) {
		if (c2b == null) {
			c2b = new byte[256];
			for (byte b = 0; b < 64; b++) {
				c2b[(byte) mapTable[b]] = b;
			}
		}
		byte nibble[] = new byte[4];
		char decode[] = new char[es.length()];
		int d = 0;
		int n = 0;
		for (int i = 0; i < es.length(); i++) {
			char c = es.charAt(i);
			nibble[n] = c2b[c];
			if (c == '=') {
				break;
			}
			switch (n) {
			case 0: // '\0'
			{
				n++;
				break;
			}
			case 1: // '\001'
			{
				byte b = (byte) (nibble[0] * 4 + nibble[1] / 16);
				decode[d++] = (char) b;
				n++;
				break;
			}
			case 2: // '\002'
			{
				byte b = (byte) ((nibble[1] & 0xf) * 16 + nibble[2] / 4);
				decode[d++] = (char) b;
				n++;
				break;
			}
			default: {
				byte b = (byte) ((nibble[2] & 3) * 64 + nibble[3]);
				decode[d++] = (char) b;
				n = 0;
				break;
			}
			}
		}
		String decoded = new String(decode, 0, d);
		return decoded;
	}

	public static boolean verify(String ds, String es) {
		boolean bRet = false;
		bRet = ds == decode(es);
		return bRet;
	}
}