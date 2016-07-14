package huffman;

import java.util.PriorityQueue;

import huffman.tree.HuffmanLeaf;
import huffman.tree.HuffmanNode;
import huffman.tree.HuffmanTree;

/*	HuffmanBuilder.java
 *		Receives a frequency array to build the HuffmanTRee
 * 		Holder of HuffmanCode class
 * 		Generates huffman code compressed
 * 
 */

public class HuffmanBuilder {
	private HuffmanTree huffTree;
	private StringBuffer prefix;
	private Boolean printOut;
	public HuffmanCode<Integer> hc;

	public HuffmanBuilder(int[] freqArray, Boolean printOut) {
		this.printOut = printOut;
		prefix = new StringBuffer();

		hc = new HuffmanCode<Integer>();
		huffTree = buildTree(freqArray);
		generateCodes(huffTree, prefix);

	}

	private HuffmanTree buildTree(int[] freqArray) {
		PriorityQueue<HuffmanTree> trees = new PriorityQueue<HuffmanTree>();
		for (int i = 0; i < freqArray.length; i++) // loop to all possible items
			if (freqArray[i] > 0) // if the certain item exist
				trees.offer(new HuffmanLeaf<Integer>(freqArray[i], i));

		assert trees.size() > 0; // make sure that a tree exists
		while (trees.size() > 1) { // repeteadly process to come up with a big tree(total frequency)
			// two trees with least frequency is combined
			HuffmanTree a = trees.poll();
			HuffmanTree b = trees.poll();
			// the inserted back again to the queue
			trees.offer(new HuffmanNode(a, b));
		}
		return trees.poll();
	}

	private void generateCodes(HuffmanTree tree, StringBuffer prefix) {
		assert tree != null;

		if (tree instanceof HuffmanLeaf) {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			HuffmanLeaf<Integer> leaf = (HuffmanLeaf) tree;
			hc.addToList(leaf.value, leaf.frequency, prefix.toString());

			if (printOut)
				System.out.println(leaf.value + "\t" + leaf.frequency + "\t" + prefix);

		} else if (tree instanceof HuffmanNode) {
			HuffmanNode node = (HuffmanNode) tree;
			// traverse left
			prefix.append('0');
			generateCodes(node.left, prefix);
			prefix.deleteCharAt(prefix.length() - 1);
			// traverse right
			prefix.append('1');
			generateCodes(node.right, prefix);
			prefix.deleteCharAt(prefix.length() - 1);
		}
	}

	public void printTree(HuffmanTree t1) {
		if (t1 == null) {
			System.out.print("NULL");
			return;
		}

		if (t1 instanceof HuffmanNode) {
			if (((HuffmanNode) t1).left != null || ((HuffmanNode) t1).right != null) {
				System.out.print(" ( " + t1.frequency + " ");
				printTree(((HuffmanNode) t1).left);
				printTree(((HuffmanNode) t1).right);
				System.out.print(")");
			}
		} else
			System.out.print(" " + ((HuffmanLeaf<?>) t1).value + " ");
	}

}
