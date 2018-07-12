package com.elex.hoe.table;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import com.elex.hoe.table.ActionTableOuterClass.ActionTable;
import com.elex.hoe.table.TableDataOuterClass.TableData;

public class TestMain {

	public static void main(String[] args) {
		
		byte[] data;
		try {
//			data = doRead("E:\\GitHub\\tools\\proto\\table_data\\ActionTable.bytes");
//			ActionTable pb = ActionTable.parseFrom(data);
			data = doRead("E:\\GitHub\\tools\\proto\\table_data\\TableData.bytes");
			TableData pb = TableData.parseFrom(data);
			System.out.println(pb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static byte[] doRead(String filePath) throws IOException {
		FileInputStream aFilein = new FileInputStream(filePath);
		byte[] data = new byte[aFilein.available()];
		aFilein.read(data);
		aFilein.close();
		return data;
	}
}
