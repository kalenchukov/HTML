# HTML

Работа с HTML.

## Использование

#### Удаление HTML-комментариев

```java
String text = """
<html><!-- Комментарий --></html>
<!--
    Комментарий
    с переносом
-->
""";

Html html = new Html(text);
html.deleteComments();

/*
Результат выполнения:
<html></html>
*/
```

#### Удаление разделов CDATA

```java
String text = """
<html>
<![CDATA[x<y]]>Текст<![CDATA[
cdata ->
text
]]>
</html>
""";

Html html = new Html(text);
html.deleteCData();

/*
Результат выполнения:
<html>
Текст
</html>
*/
```

#### Удаление типа документа

```java
String text = """
<!DOCTYPE html>
<b><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"></b>
""";

Html html = new Html(text);
html.deleteDoctype();

/*
Результат выполнения:
<b></b>
*/
```

#### Удаление HTML-тега

```java
String text = """
text <a href="/link" target='_blank'>ссылка</a> Текст
""";

Html html = new Html(text);
html.deleteTag(Tag.A);

/*
Результат выполнения:
text ссылка Текст
*/
```

#### Удаление HTML-тега указанного типа

```java
String text = """
text <a href="/link" target='_blank'>ссылка</a> Текст
""";

Html html = new Html(text);
html.deleteTag(Tag.A, TagType.START);

/*
Результат выполнения:
text ссылка</a> Текст
*/
```

#### Удаление всех HTML-тегов

```java
String text = """
<b>text</b> <a href="/link" target='_blank'>ссылка</a> Текст <br/>
""";

Html html = new Html(text);
html.deleteTags();

/*
Результат выполнения:
text ссылка Текст 
*/
```

#### Удаление всех HTML-тегов указанного типа

```java
String text = """
<b>text</b> <a href="/link" target='_blank'>ссылка</a> Текст <br/>
""";

Html html = new Html(text);
html.deleteTags(TagType.START);

/*
Результат выполнения:
text</b> ссылка</a> Текст <br/>
*/
```

#### Удаление всех HTML-тегов с указанием исключений

```java
String text = """
<b>text</b> <a href="/link" target='_blank'>ссылка</a> Текст <br/>
""";

Html html = new Html(text);
html.deleteTags(List.of(Tag.BR));

/*
Результат выполнения:
text ссылка Текст <br/>
*/
```

#### Удаление всех HTML-тегов с указанного типа и исключений

```java
String text = """
<b>text</b> <a href="/link" target='_blank'>ссылка</a> Текст <br/>
""";

Html html = new Html(text);
html.deleteTags(TagType.START, List.of(Tag.B));

/*
Результат выполнения:
<b>text</b> ссылка</a> Текст <br/>
*/
```

#### Удаление HTML-сущности

```java
String text = """
AC&sol;DC
&lt;&#60;&#x3C;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;
""";

Html html = new Html(text);
html.deleteEntity(Entity.LT);

/*
Результат выполнения:
AC&sol;DC
переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;
*/
```

#### Удаление HTML-сущности указанного типа

```java
String text = """
AC&sol;DC
&lt;&#60;&#x3C;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;
""";

Html html = new Html(text);
html.deleteEntity(Entity.LT, EntityType.NUMERIC);

/*
Результат выполнения:
AC&sol;DC
&lt;&#x3C;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;
*/
```

#### Удаление всех HTML-сущностей

```java
String text = """
AC&sol;DC
&lt;&#60;&#x3C;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;
""";

Html html = new Html(text);
html.deleteEntities();

/*
Результат выполнения:
ACDC
переменный токпостоянный ток
*/
```

#### Удаление всех HTML-сущностей с указанием исключений

```java
String text = """
AC&sol;DC
&lt;&#60;&#x3C;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;
""";

Html html = new Html(text);
html.deleteEntities(List.of(Entity.LT, Entity.GT));

/*
Результат выполнения:
ACDC
&lt;&#60;&#x3C;переменный токпостоянный ток&#62;&#X0003E;&gt;
*/
```

#### Преобразование специальных символов в соответствующие HTML-сущности с указанием типа

```java
String text = """
AC/DC
<<<переменный ток/постоянный ток>>>;
""";

Html html = new Html(text);
html.encodeEntity(Entity.LT, EntityType.NAME);

/*
Результат выполнения:
AC/DC
&lt;&lt;&lt;переменный ток/постоянный ток>>>
*/
```

#### Преобразование всех специальных символов в соответствующие HTML-сущности с указанием типа

```java
String text = """
AC/DC
<<<переменный ток/постоянный ток>>>;
""";

Html html = new Html(text);
html.encodeEntities(EntityType.NAME);

/*
Результат выполнения:
AC&sol;DC
&lt;&lt;&lt;переменный ток&sol;постоянный ток&gt;&gt;&gt;
*/
```

#### Преобразование всех специальных символов в соответствующие HTML-сущности с указанием типа и исключений

```java
String text = """
AC/DC
<<<переменный ток/постоянный ток>>>;
""";

Html html = new Html(text);
html.encodeEntities(EntityType.NAME, List.of(Entity.GT));

/*
Результат выполнения:
AC&sol;DC
&lt;&lt;&lt;переменный ток&sol;постоянный ток>>>
*/
```

#### Преобразование HTML-сущности в соответствующий специальный символ

```java
String text = """
AC&sol;DC
&lt;&lt;&lt;переменный ток&sol;постоянный ток&gt;&gt;&gt;
""";

Html html = new Html(text);
html.decodeEntity(Entity.GT);

/*
Результат выполнения:
AC&sol;DC
&lt;&lt;&lt;переменный ток&sol;постоянный ток>>>
*/
```

#### Преобразование HTML-сущности в соответствующий специальный символ с указанием типа

```java
String text = """
AC&sol;DC
&lt;&lt;&lt;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;
""";

Html html = new Html(text);
html.decodeEntity(Entity.GT, EntityType.NAME);

/*
Результат выполнения:
AC&sol;DC
&lt;&lt;&lt;переменный ток&sol;постоянный ток&#62;&#X0003E;>
*/
```

#### Преобразование всех HTML-сущностей в соответствующие специальные символы

```java
String text = """
AC&sol;DC
&lt;&lt;&lt;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;
""";

Html html = new Html(text);
html.decodeEntities();

/*
Результат выполнения:
AC/DC
<<<переменный ток/постоянный ток>>>
*/
```

#### Преобразование всех HTML-сущностей в соответствующие специальные символы с указанием типа

```java
String text = """
AC&sol;DC
&lt;&lt;&lt;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;
""";

Html html = new Html(text);
html.decodeEntities(EntityType.NAME);

/*
Результат выполнения:
AC/DC
<<<переменный ток/постоянный ток&#62;&#X0003E;>
*/
```

#### Преобразование всех HTML-сущностей в соответствующие специальные символы с указанием исключений

```java
String text = """
AC&sol;DC
&lt;&lt;&lt;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;
""";

Html html = new Html(text);
html.decodeEntities(List.of(Entity.LT, Entity.GT));

/*
Результат выполнения:
AC/DC
&lt;&lt;&lt;переменный ток/постоянный ток&#62;&#X0003E;&gt;
*/
```

#### Преобразование всех HTML-сущностей в соответствующие специальные символы с указанием типа и исключений

```java
String text = """
AC&sol;DC
&lt;&lt;&lt;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;
""";

Html html = new Html(text);
html.decodeEntities(EntityType.NAME, List.of(Entity.LT));

/*
Результат выполнения:
AC/DC
&lt;&lt;&lt;переменный ток/постоянный ток&#62;&#X0003E;>
*/
```
