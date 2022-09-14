/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.kalenchukov.html.resources;

import org.junit.Test;

import static org.junit.Assert.*;

public class EntityTest
{
	@Test
	public void getSymbol()
	{
		assertEquals(Character.valueOf('#'), Entity.NUM.getSymbol());
	}

	@Test
	public void getEntity()
	{
		assertEquals("num", Entity.NUM.getEntity(EntityType.NAME));
		assertEquals("35", Entity.NUM.getEntity(EntityType.NUMERIC));
		assertEquals("23", Entity.NUM.getEntity(EntityType.UNICODE));
	}

	@Test
	public void getMnemonic()
	{
		assertEquals("&num;", Entity.NUM.getMnemonic(EntityType.NAME));
		assertEquals("&#35;", Entity.NUM.getMnemonic(EntityType.NUMERIC));
		assertEquals("&#X23;", Entity.NUM.getMnemonic(EntityType.UNICODE));
	}

	@Test
	public void getEntityName()
	{
		assertEquals("num", Entity.NUM.getEntityName());
	}

	@Test
	public void getEntityNumeric()
	{
		assertEquals("35", Entity.NUM.getEntityNumeric());
	}

	@Test
	public void getEntityUnicode()
	{
		assertEquals("23", Entity.NUM.getEntityUnicode());
	}

	@Test
	public void getMnemonicName()
	{
		assertEquals("&num;", Entity.NUM.getMnemonicName());
	}

	@Test
	public void getMnemonicNumeric()
	{
		assertEquals("&#35;", Entity.NUM.getMnemonicNumeric());
	}

	@Test
	public void getMnemonicUnicode()
	{
		assertEquals("&#X23;", Entity.NUM.getMnemonicUnicode());
	}
}