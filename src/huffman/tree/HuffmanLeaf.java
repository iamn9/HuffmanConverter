package huffman.tree;

public class HuffmanLeaf<G> extends HuffmanTree {
	public final G value;

	public HuffmanLeaf(int freq, G val) {
		super(freq);
		value = val;
	}
}