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
 * @author Алексей Каленчуков
 */
public class EntityTest
{
	/**
	 * Проверка метода {@link Entity#getSymbol()}.
	 */
	@Test
	public void getSymbol()
	{
		Entity entity = Entity.NUM;

		Character actualSymbol = entity.getSymbol();

		assertEquals('#', actualSymbol);
	}

	/**
	 * Проверка метода {@link Entity#getEntity(EntityType)}.
	 */
	@Test
	public void getEntityTypeName()
	{
		Entity entity = Entity.NUM;

		String actualEntity = entity.getEntity(EntityType.NAME);

		assertEquals("num", actualEntity);
	}

	/**
	 * Проверка метода {@link Entity#getEntity(EntityType)}.
	 */
	@Test
	public void getEntityTypeNumeric()
	{
		Entity entity = Entity.NUM;

		String actualEntity = entity.getEntity(EntityType.NUMERIC);

		assertEquals("35", actualEntity);
	}

	/**
	 * Проверка метода {@link Entity#getEntity(EntityType)}.
	 */
	@Test
	public void getEntityTypeUnicode()
	{
		Entity entity = Entity.NUM;

		String actualEntity = entity.getEntity(EntityType.UNICODE);

		assertEquals("23", actualEntity);
	}

	/**
	 * Проверка метода {@link Entity#getMnemonic(EntityType)}.
	 */
	@Test
	public void getMnemonicTypeName()
	{
		Entity entity = Entity.NUM;

		String actualMnemonic = entity.getMnemonic(EntityType.NAME);

		assertEquals("&num;", actualMnemonic);
	}

	/**
	 * Проверка метода {@link Entity#getMnemonic(EntityType)}.
	 */
	@Test
	public void getMnemonicTypeNumeric()
	{
		Entity entity = Entity.NUM;

		String actualMnemonic = entity.getMnemonic(EntityType.NUMERIC);

		assertEquals("&#35;", actualMnemonic);
	}

	/**
	 * Проверка метода {@link Entity#getMnemonic(EntityType)}.
	 */
	@Test
	public void getMnemonicTypeUnicode()
	{
		Entity entity = Entity.NUM;

		String actualMnemonic = entity.getMnemonic(EntityType.UNICODE);

		assertEquals("&#X23;", actualMnemonic);
	}

	/**
	 * Проверка метода {@link Entity#getEntityName()}.
	 */
	@Test
	public void getEntityName()
	{
		Entity entity = Entity.NUM;

		String actualEntityName = entity.getEntityName();

		assertEquals("num", actualEntityName);
	}

	/**
	 * Проверка метода {@link Entity#getEntityNumeric()}.
	 */
	@Test
	public void getEntityNumeric()
	{
		Entity entity = Entity.NUM;

		String actualEntityNumeric = entity.getEntityNumeric();

		assertEquals("35", actualEntityNumeric);
	}

	/**
	 * Проверка метода {@link Entity#getEntityUnicode()}.
	 */
	@Test
	public void getEntityUnicode()
	{
		Entity entity = Entity.NUM;

		String actualUnicode = entity.getEntityUnicode();

		assertEquals("23", actualUnicode);
	}

	/**
	 * Проверка метода {@link Entity#getMnemonicName()}.
	 */
	@Test
	public void getMnemonicName()
	{
		Entity entity = Entity.NUM;

		String MnemonicName = entity.getMnemonicName();

		assertEquals("&num;", MnemonicName);
	}

	/**
	 * Проверка метода {@link Entity#getMnemonicNumeric()}.
	 */
	@Test
	public void getMnemonicNumeric()
	{
		Entity entity = Entity.NUM;

		String actualMnemonicNumeric = entity.getMnemonicNumeric();

		assertEquals("&#35;", actualMnemonicNumeric);
	}

	/**
	 * Проверка метода {@link Entity#getMnemonicUnicode()}.
	 */
	@Test
	public void getMnemonicUnicode()
	{
		Entity entity = Entity.NUM;

		String actualMnemonicUnicode = entity.getMnemonicUnicode();

		assertEquals("&#X23;", actualMnemonicUnicode);
	}
}