/**   
 * License Agreement for OpenSearchServer
 *
 * Copyright (C) 2013-2014 Emmanuel Keller / Jaeksoft
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

package com.jaeksoft.searchlib.index.osse.memory;

import java.io.UnsupportedEncodingException;
import java.nio.charset.CharacterCodingException;

import com.jaeksoft.searchlib.index.osse.OsseTermBuffer;
import com.jaeksoft.searchlib.util.StringUtils;
import com.sun.jna.Pointer;

/**
 * This class implements a fast UTF-8 String array *
 */
public class OsseFastStringArray extends Pointer {

	/**
	 * Optimized write only StringArray
	 * 
	 * @param strings
	 */

	private final DisposableMemory termPointers;

	public OsseFastStringArray(final MemoryBuffer memoryBuffer,
			final OsseTermBuffer termBuffer)
			throws UnsupportedEncodingException, CharacterCodingException {
		super(0);

		// First we reserve memory for the list of pointers
		final int termCount = termBuffer.getTermCount();
		termPointers = memoryBuffer.getNewBufferItem(termCount * Pointer.SIZE);
		peer = termPointers.getPeer();

		// Filling the pointer array memory
		// OsseTerm[] terms = termBuffer.getTerms();
		Pointer[] pointers = new Pointer[termCount];
		for (int i = 0; i < termCount; i++) {
			// OsseTerm term = terms[i];
			// pointers[i] = new Pointer(term.memory.getPeer() + term.offset);
		}
		termPointers.write(0, pointers, 0, termCount);
	}

	final public void close() {
		termPointers.close();
	}

	@Override
	public String toString() {
		return StringUtils.fastConcat("[", super.toString(), " ", "]");
	}
}
