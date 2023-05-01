/*
 * Copyright © 2022-2023 Алексей Каленчуков
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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс проверки констант и методов перечисления {@link Entity}.
 *
 * @author Aleksey Kalenchukov
 */
public class EntityTest
{
	/**
	 * Проверка метода {@link Entity#getSymbol()}.
	 */
	@Test
	public void testGetSymbol()
	{
		assertEquals(Character.valueOf('#'), Entity.NUM.getSymbol());
	}

	/**
	 * Проверка метода {@link Entity#getEntity(EntityType)}.
	 */
	@Test
	public void testGetEntity()
	{
		assertEquals("num", Entity.NUM.getEntity(EntityType.NAME));
		assertEquals("35", Entity.NUM.getEntity(EntityType.NUMERIC));
		assertEquals("23", Entity.NUM.getEntity(EntityType.UNICODE));
	}

	/**
	 * Проверка метода {@link Entity#getMnemonic(EntityType)}.
	 */
	@Test
	public void testGetMnemonic()
	{
		assertEquals("&num;", Entity.NUM.getMnemonic(EntityType.NAME));
		assertEquals("&#35;", Entity.NUM.getMnemonic(EntityType.NUMERIC));
		assertEquals("&#X23;", Entity.NUM.getMnemonic(EntityType.UNICODE));
	}

	/**
	 * Проверка метода {@link Entity#getEntityName()}.
	 */
	@Test
	public void testGetEntityName()
	{
		assertEquals("num", Entity.NUM.getEntityName());
	}

	/**
	 * Проверка метода {@link Entity#getEntityNumeric()}.
	 */
	@Test
	public void testGetEntityNumeric()
	{
		assertEquals("35", Entity.NUM.getEntityNumeric());
	}

	/**
	 * Проверка метода {@link Entity#getEntityUnicode()}.
	 */
	@Test
	public void testGetEntityUnicode()
	{
		assertEquals("23", Entity.NUM.getEntityUnicode());
	}

	/**
	 * Проверка метода {@link Entity#getMnemonicName()}.
	 */
	@Test
	public void testGetMnemonicName()
	{
		assertEquals("&num;", Entity.NUM.getMnemonicName());
	}

	/**
	 * Проверка метода {@link Entity#getMnemonicNumeric()}.
	 */
	@Test
	public void testGetMnemonicNumeric()
	{
		assertEquals("&#35;", Entity.NUM.getMnemonicNumeric());
	}

	/**
	 * Проверка метода {@link Entity#getMnemonicUnicode()}.
	 */
	@Test
	public void testGetMnemonicUnicode()
	{
		assertEquals("&#X23;", Entity.NUM.getMnemonicUnicode());
	}
}