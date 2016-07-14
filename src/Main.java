import java.util.Scanner;

import huffman.HuffmanBuilder;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("String to Huffman Code Converter");

		System.out.print("Input string: ");
		String input = in.nextLine();

		char[] inputInArray = input.toCharArray();
		int[] freq = new int[256];
		for (int i = 0; i < inputInArray.length; i++) {
			freq[inputInArray[i]]++;
		}

		HuffmanBuilder hb = new HuffmanBuilder(freq, false);

		System.out.print("Get code of character: ");
		String search = in.nextLine();
		System.out.println();
		String code = hb.hc.getCode((int) search.charAt(0));
		int frequency = hb.hc.getFrequency((int) search.charAt(0));

		System.out.print("\nHuffman String: ");
		for (char oneChar : inputInArray) {
			System.out.print(hb.hc.getCode(oneChar));
		}
		System.out.println("\nCode: " + code);
		System.out.println("Frequency: " + frequency + "\n");
		hb.hc.printTable();
		in.close();
	}

}
