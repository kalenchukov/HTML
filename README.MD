# Справка
## HTML
Работа с HTML.

## Использование
### Получение шаблона регулярного выражения
#### Область CDATA
```java
Regexp.CDATA;
```

#### Самозакрывающийся HTML-тег
```java
Regexp.SELF_CLOSING_TAG;
```

#### Открывающий HTML-тег
```java
Regexp.OPEN_TAG;
```

#### Закрывающий HTML-тег
```java
Regexp.CLOSE_TAG;
```

#### HTML-комментарий
```java
Regexp.COMMENT;
```

#### Тип HTML-документа
```java
Regexp.DOCTYPE;
```

#### HTML-сущность в виде имени
```java
Regexp.ENTITY_NAME;
```

#### HTML-сущность в виде unicode
```java
Regexp.ENTITY_UNICODE;
```

#### HTML-сущность в виде числа
```java
Regexp.ENTITY_NUMERIC;
```

### Проверка
#### Проверка HTML комментария
```java
String text = "<!-- Комментарий -->";

Html html = new Html(text);
html.isComment();
```

Результат выполнения:
```
true
```

#### Проверка HTML сущности
```java
String text = "&asymp;";

Html html = new Html(text);
html.isEntity();
```

Результат выполнения:
```
true
```

#### Проверка HTML сущности в виде имени
```java
String text = "&asymp;";

Html html = new Html(text);
html.isEntityName();
```

Результат выполнения:
```
true
```

#### Проверка HTML сущности в виде числа
```java
String text = "&38;";

Html html = new Html(text);
html.isEntityNumeric();
```

Результат выполнения:
```
true
```

#### Проверка HTML сущности в виде unicode
```java
String text = "&#X22C8;";

Html html = new Html(text);
html.isEntityUnicode();
```

Результат выполнения:
```
true
```

#### Проверка типа HTML документа
```java
String text = "<!DOCTYPE html>";

Html html = new Html(text);
html.isDoctype();
```

Результат выполнения:
```
true
```

#### Проверка HTML тега
```java
String text = "<b class='xa-xa'>";

Html html = new Html(text);
html.isTag();
```

Результат выполнения:
```
true
```

#### Проверка открывающего HTML тега
```java
String text = "<input type='checkbox'>";

Html html = new Html(text);
html.isOpenTag();
```

Результат выполнения:
```
true
```

#### Проверка самозакрывающегося HTML тега
```java
String text = "<input type='checkbox' />";

Html html = new Html(text);
html.isSelfClosingTag();
```

Результат выполнения:
```
true
```

#### Проверка закрывающего HTML тега
```java
String text = "</form>";

Html html = new Html(text);
html.isCloseTag();
```

Результат выполнения:
```
true
```

#### Проверка области CData
```java
String text = "<![CDATA[CDATA область]]>";

Html html = new Html(text);
html.isCData();
```

Результат выполнения:
```
true
```

### Поиск
#### Поиск HTML комментариев
```java
String text = """
    Песня без слов, ночь без сна
    <!--Всё в свое время, зима и весна-->
    Каждой звезде свой неба кусок
    Каждому морю дождя глоток
    Каждому <!-- яблоку место упасть
    Каждому вору возможность украсть
    Каждой собаке палку и кость-->
    И каждому волку зубы и злость
    """;

Html html = new Html(text);
html.findComments();
```

Результат выполнения:
```
[
    "<!--Всё в свое время, зима и весна-->",
    "<!-- яблоку место упасть\nКаждому вору возможность украсть\nКаждой собаке палку и кость-->"
]
```

#### Поиск областей CDATA
```java
String text = """
    <![CDATA[Здесь не понятно, где лицо, а где рыло,]]>
    И не понятно, где пряник, где плеть.
    <![CDATA[Здесь в сено не втыкаются вилы,
    А рыба проходит сквозь сеть.
    И не ясно, где море, где суша,
    Где золото, а где медь.]]>
    Что построить, и что разрушить,
    И кому, и зачем здесь петь?
    """;

Html html = new Html(text);
html.findCData();
```

Результат выполнения:
```
[
    "<![CDATA[Здесь не понятно, где лицо, а где рыло,]]>",
    "<![CDATA[Здесь в сено не втыкаются вилы,\nА рыба проходит сквозь сеть.\nИ не ясно, где море, где суша,\nГде золото, а где медь.]]>"
]
```

#### Поиск HTML-сущностей
```java
String text = """
    Как много веселых ребят,
    И все делают &frac14; велосипед,
    А один из них как-нибудь утром придумает порох.
    Ну а я здесь сижу &#44;без тебя,&amp;
    Мне до этих ребят дела нет,
    Лишь окурки лежат на полу, да мусора ворох.&#169;
    """;

Html html = new Html(text);
html.findEntities();
```

Результат выполнения:
```
[
    "&frac14;",
    "&amp;",
    "&#44;",
    "&#169;"
]
```

#### Поиск HTML-сущностей в виде имени
```java
String text = """
    Как много веселых ребят,
    И все делают &frac14; велосипед,
    А один из них как-нибудь утром придумает порох.
    Ну а я здесь сижу без тебя,&amp;
    Мне до этих ребят дела нет,
    Лишь окурки лежат на полу, да мусора ворох.
    """;

Html html = new Html(text);
html.findEntitiesName();
```

Результат выполнения:
```
[
    "&frac14;",
    "&amp;"
]
```

#### Поиск HTML-сущностей в виде числа
```java
String text = """
    В моем доме не видно стены,
    В моем небе &#44;не видно луны.
    Я слеп, но я вижу тебя,
    Я глух, но я слышу тебя.
    Я не сплю, &#169; но я вижу сны,
    Здесь нет моей вины,
    Я нем, но ты слышишь меня,
    И этим мы сильны.
    """;

Html html = new Html(text);
html.findEntitiesNumeric();
```

Результат выполнения:
```
[
    "&#44;",
    "&#169;"
]
```

#### Поиск HTML-сущностей в виде unicode
```java
String text = """
    Ночь, день -
    Спать &#X22C8;лень.
    Есть дым -&#x224E;
    Чёрт с ним.
    Сна нет -
    Есть сон лет.
    Кино -
    Кончилось давно.
    """;

Html html = new Html(text);
html.findEntitiesUnicode();
```

Результат выполнения:
```
[
    "&#X22C8;",
    "&#x224E;"
]
```

#### Поиск типа HTML документа
```java
String text = """
    <!DOCTYPE html>Они говорят: им нельзя рисковать,
    Потому что у них есть дом,
    В доме горит свет.
    И я не знаю точно, <!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN'
    'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'> кто из нас прав,
    Меня ждет на улице дождь,
    Их ждет дома обед.
    """;

Html html = new Html(text);
html.findDoctype();
```

Результат выполнения:
```
[
    "<!DOCTYPE html>",
    "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN'\n'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>"
]
```

#### Поиск HTML тегов
```java
String text = """
    Солдат шел по улице домой
    И <b>увидел этих ребят.</b >
    "Кто ваша мама, ребята?" -
    <meta charset='UTF-8'>Спросил у ребят солдат.
    """;

Html html = new Html(text);
html.findTags();
```

Результат выполнения:
```
[
    "<b>",
    "</b >",
    "<meta charset='UTF-8'>"
]
```

#### Поиск открывающих HTML тегов
```java
String text = """
    Солдат шел по улице домой
    И <b>увидел этих ребят.
    "Кто ваша мама, ребята?" -
    <meta charset='UTF-8'>Спросил у ребят солдат.
    """;

Html html = new Html(text);
html.findOpenTags();
```

Результат выполнения:
```
[
    "<b>",
    "<meta charset='UTF-8'>"
]
```

#### Поиск закрывающих HTML тегов
```java
String text = """
    О-o, это странное место Камчатка,
    О-o, это сладкое</form> слово "Камчатка".
    Но на этой земле я не вижу тебя,
    Я не вижу твоих кораблей,
    Я не вижу реки, я не вижу моста,
    Ну и пусть...</b >
    """;

Html html = new Html(text);
html.findCloseTags();
```

Результат выполнения:
```
[
    "</form>",
    "</b >"
]
*/

```
#### Поиск самозакрывающихся HTML тегов
```java
String text = """
    У меня есть дом, только нет ключей,
    У меня есть солнце, но оно среди туч,<br/>
    Есть голова<meta charset='UTF-8'/>, только нет плечей,
    Но я вижу, как тучи режут солнечный луч.
    У меня есть слово, но в нем нет букв,
    У меня есть лес, но нет топоров,
    У меня есть время, но нет сил ждать,
    И есть еще ночь, но в ней нет снов.
    """;

Html html = new Html(text);
html.findSelfClosingTags();
```

Результат выполнения:
```
[
    "<br/>",
    "<meta charset='UTF-8'/>"
]
```

### Преобразование
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

```

Результат выполнения:
```
<html></html>
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
```

Результат выполнения:
```
<html>
Текст
</html>
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
```

Результат выполнения:
```
<b></b>
```

#### Удаление HTML-тега
```java
String text = """
    text <a href="/link" target='_blank'>ссылка</a> Текст
    """;

Html html = new Html(text);
html.deleteTag(Tag.A);
```

Результат выполнения:
```
text ссылка Текст
```

#### Удаление HTML-тега указанного типа
```java
String text = """
    text <a href="/link" target='_blank'>ссылка</a> Текст
    """;

Html html = new Html(text);
html.deleteTag(Tag.A, TagType.OPEN);
```

Результат выполнения:
```
text ссылка</a> Текст
```

#### Удаление всех HTML-тегов
```java
String text = """
    <b>text</b> <a href="/link" target='_blank'>ссылка</a> Текст <br/>
    """;

Html html = new Html(text);
html.deleteTags();
```

Результат выполнения:
```
text ссылка Текст 
```

#### Удаление всех HTML-тегов указанного типа
```java
String text = """
    <b>text</b> <a href="/link" target='_blank'>ссылка</a> Текст <br/>
    """;

Html html = new Html(text);
html.deleteTags(TagType.OPEN);
```

Результат выполнения:
```
text</b> ссылка</a> Текст <br/>
```

#### Удаление всех HTML-тегов с указанием исключений
```java
String text = """
    <b>text</b> <a href="/link" target='_blank'>ссылка</a> Текст <br/>
    """;

Html html = new Html(text);
html.deleteTags(List.of(Tag.BR));
```

Результат выполнения:
```
text ссылка Текст <br/>
```

#### Удаление всех HTML-тегов с указанного типа и исключений
```java
String text = """
    <b>text</b> <a href="/link" target='_blank'>ссылка</a> Текст <br/>
    """;

Html html = new Html(text);
html.deleteTags(TagType.OPEN, List.of(Tag.B));
```

Результат выполнения:
```
<b>text</b> ссылка</a> Текст <br/>
```

#### Удаление HTML-сущности
```java
String text = """
    AC&sol;DC
    &lt;&#60;&#x3C;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;
    """;

Html html = new Html(text);
html.deleteEntity(Entity.LT);
```

Результат выполнения:
```
AC&sol;DC
переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;
```

#### Удаление HTML-сущности указанного типа
```java
String text = """
    AC&sol;DC
    &lt;&#60;&#x3C;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;
    """;

Html html = new Html(text);
html.deleteEntity(Entity.LT, EntityType.NUMERIC);
```

Результат выполнения:
```
AC&sol;DC
&lt;&#x3C;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;
```

#### Удаление всех HTML-сущностей
```java
String text = """
    AC&sol;DC
    &lt;&#60;&#x3C;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;
    """;

Html html = new Html(text);
html.deleteEntities();
```

Результат выполнения:
```
ACDC
переменный токпостоянный ток
```

#### Удаление всех HTML-сущностей с указанием исключений
```java
    String text = """
    AC&sol;DC
    &lt;&#60;&#x3C;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;
    """;

Html html = new Html(text);
html.deleteEntities(List.of(Entity.LT, Entity.GT));
```

Результат выполнения:
```
ACDC
&lt;&#60;&#x3C;переменный токпостоянный ток&#62;&#X0003E;&gt;
```

#### Преобразование специальных символов в соответствующие HTML-сущности с указанием типа
```java
String text = """
    AC/DC
    <<<переменный ток/постоянный ток>>>;
    """;

Html html = new Html(text);
html.encodeEntity(Entity.LT, EntityType.NAME);
```

Результат выполнения:
```
AC/DC
&lt;&lt;&lt;переменный ток/постоянный ток>>>
```

#### Преобразование всех специальных символов в соответствующие HTML-сущности с указанием типа
```java
String text = """
    AC/DC
    <<<переменный ток/постоянный ток>>>;
    """;

Html html = new Html(text);
html.encodeEntities(EntityType.NAME);
```

Результат выполнения:
```
AC&sol;DC
&lt;&lt;&lt;переменный ток&sol;постоянный ток&gt;&gt;&gt;
```

#### Преобразование всех специальных символов в соответствующие HTML-сущности с указанием типа и исключений
```java
String text = """
    AC/DC
    <<<переменный ток/постоянный ток>>>;
    """;

Html html = new Html(text);
html.encodeEntities(EntityType.NAME, List.of(Entity.GT));
```

Результат выполнения:
```
AC&sol;DC
&lt;&lt;&lt;переменный ток&sol;постоянный ток>>>
```

#### Преобразование HTML-сущности в соответствующий специальный символ
```java
String text = """
    AC&sol;DC
    &lt;&lt;&lt;переменный ток&sol;постоянный ток&gt;&gt;&gt;
    """;

Html html = new Html(text);
html.decodeEntity(Entity.GT);
```

Результат выполнения:
```
AC&sol;DC
&lt;&lt;&lt;переменный ток&sol;постоянный ток>>>
```

#### Преобразование HTML-сущности в соответствующий специальный символ с указанием типа
```java
String text = """
    AC&sol;DC
    &lt;&lt;&lt;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;
    """;

Html html = new Html(text);
html.decodeEntity(Entity.GT, EntityType.NAME);
```

Результат выполнения:
```
AC&sol;DC
&lt;&lt;&lt;переменный ток&sol;постоянный ток&#62;&#X0003E;>
```

#### Преобразование всех HTML-сущностей в соответствующие специальные символы
```java
String text = """
    AC&sol;DC
    &lt;&lt;&lt;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;
    """;

Html html = new Html(text);
html.decodeEntities();
```

Результат выполнения:
```
AC/DC
<<<переменный ток/постоянный ток>>>
```

#### Преобразование всех HTML-сущностей в соответствующие специальные символы с указанием типа
```java
String text = """
    AC&sol;DC
    &lt;&lt;&lt;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;
    """;

Html html = new Html(text);
html.decodeEntities(EntityType.NAME);
```

Результат выполнения:
```
AC/DC
<<<переменный ток/постоянный ток&#62;&#X0003E;>
```

#### Преобразование всех HTML-сущностей в соответствующие специальные символы с указанием исключений
```java
String text = """
    AC&sol;DC
    &lt;&lt;&lt;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;
    """;

Html html = new Html(text);
html.decodeEntities(List.of(Entity.LT, Entity.GT));
```

Результат выполнения:
```
AC/DC
&lt;&lt;&lt;переменный ток/постоянный ток&#62;&#X0003E;&gt;
```

#### Преобразование всех HTML-сущностей в соответствующие специальные символы с указанием типа и исключений
```java
String text = """
    AC&sol;DC
    &lt;&lt;&lt;переменный ток&sol;постоянный ток&#62;&#X0003E;&gt;
    """;

Html html = new Html(text);
html.decodeEntities(EntityType.NAME, List.of(Entity.LT));
```

Результат выполнения:
```
AC/DC
&lt;&lt;&lt;переменный ток/постоянный ток&#62;&#X0003E;>
```
