/**   
 * License Agreement for OpenSearchServer
 *
 * Copyright (C) 2012 Emmanuel Keller / Jaeksoft
 * 
 * http://www.open-search-server.com
 * 
 * This file is part of OpenSearchServer.
 *
 * OpenSearchServer is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 * OpenSearchServer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenSearchServer. 
 *  If not, see <http://www.gnu.org/licenses/>.
 **/

package com.jaeksoft.searchlib.analysis.filter.stop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrefixArray extends WordArray {

	protected String[] fixArray;
	protected String tokenSeparator;

	public PrefixArray(WordArray wordArray, boolean ignoreCase,
			String tokenSeparator) throws IOException {
		super(wordArray, ignoreCase);
		this.tokenSeparator = tokenSeparator;
		List<String> fixList = new ArrayList<String>();
		for (String word : wordSet)
			fixList.add(putWord(word));
		fixArray = new String[fixList.size()];
		fixList.toArray(fixArray);
	}

	protected String putWord(String word) {
		StringBuffer sb = new StringBuffer(word);
		if (tokenSeparator != null && tokenSeparator.length() > 0)
			sb.append(tokenSeparator);
		return sb.toString();
	}

	@Override
	public boolean match(String term) {
		for (String fix : fixArray)
			if (term.startsWith(fix))
				return true;
		return wordSet.contains(term);
	}

}
