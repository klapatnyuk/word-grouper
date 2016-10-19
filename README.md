### Задача ###
Сгруппировать слова, состоящие из одинакового набора символов.

##### Дано #####
["ток", "торс", "кот", "рост", "фывап", "Кто"]
##### Результат #####
[["ток", "кот", "Кто"], ["рост", "торс"], ["фывап"]]

### Решение ###

##### SortGrouper #####

1. Приводит слово к нижнему регистру.
2. Упорядочивает символы слова.
3. Группирует слова в HashMap,
где ключ - результат пунктов 1 и 2, значение - коллекция оригинальных слов с одинаковыми ключами.

Решение "в лоб".

##### LazyGrouper #####

1. Преобразует слово в SortedSet символов (избегая упорядочивания всех символов слова).
2. Группирует слова в дерево, где узел - одно из двух:
либо HashMap дочерних узлов, либо коллекция оригинальных слов с одинаковыми ключами.

В качестве ключа HashMap использует хэш фрагмента начала строки минимальной длины, достаточной для отделения ветви.
Механизм "ленивого" разбора позволяет обрабатывать только значимую часть слова и только в случае необходимости.
