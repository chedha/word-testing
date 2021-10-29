
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class wordTest {

	private String userWord;
	private int answer;
	

	public int wordCount(String word) {
		
		this.userWord = word;

		try {
			Document doc = Jsoup.connect("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm").get();

			Elements title = doc.getElementsByAttribute("h1");
			Elements author = doc.getElementsByClass("no-break");
			Elements poem = doc.getElementsByClass("chapter");

			try {

				FileOutputStream fileStream = null;
				PrintWriter outFS = null;
				fileStream = new FileOutputStream("RavenPoem.txt");

				outFS = new PrintWriter(fileStream);
				outFS.println(title.text());
				outFS.println(author.text());
				outFS.println(poem.text());
				outFS.close();

				FileReader file = new FileReader("RavenPoem.txt");
				BufferedReader reader = new BufferedReader(file);

				Map<String, Integer> frequency = new HashMap<>();

				String line = reader.readLine();

				while (line != null) {

					// Processing each line and splitting to separate words
					// then storing those words into array

					if (!line.trim().equals("")) {
						String[] words = line.split(" ");

						for (String value : words) {

							if (value == null || value.trim().equals("")) {
								continue;
							}
							String processed = value.toLowerCase();

							// Removing special characters

							processed = processed.replaceAll("[^a-zA-Z0-9]", "");

							// searching for current word in keyset
							// if word is found, incrementing the integer value

							if (frequency.containsKey(processed)) {
								frequency.put(processed, frequency.get(processed) + 1);
							} else {
								frequency.put(processed, 1);

							}

						}

					}

					line = reader.readLine();

				}

				answer = wordOccurences(userWord, frequency);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return answer;

	}

	static int wordOccurences(String word, Map<String, Integer> freq) {

		int mostFrequentlyUsed = 0;
		HashMap<String, Integer> frequency = new HashMap<>(freq);
		String clientInput = word.toLowerCase();

		for (String x : frequency.keySet()) {
			String theWord = null;
			Integer theVal = frequency.get(x);
			if (clientInput.equals(x)) {
				theWord = x;
				mostFrequentlyUsed = theVal;

			}

		}

		if (mostFrequentlyUsed > 0)
			return mostFrequentlyUsed;
		else
			return 0;

	}

}
