# HTTP-JSON-Wiremock

Приложение симулирует бойцовский клуб покемонов. Его функционал:
1. Вычитывать показатели покемонов из внешнего REST-сервиса
2. Инициировать бой
3. Объявлять победителя

В этом задании мы будем использовать публичный [PokeAPI](https://pokeapi.co/). Это обыкновенный REST-сервис, который хранит
в себе покемонов и их различные атрибуты. Его мы будем использовать в качестве источника наших данных. 

Приведем пример алгоритма боя. У каждого покемона есть такие поля, как `id`, `hp`, `attack` и `defense`.
* Сначала по имени покемона мы формируем запрос к PokeApi и создаём объект `Pokemon`
* Бой начинает покемон с наибольшим `id`. Он атакует своего противника на величину поля `attack` и уменьшает число `hp`. 
При уменьшении `hp`, это число уменьшается в зависимости от параметра `defense` у противника по формуле:
`(входящий урон = attack - (attack * defense/100))`. Например: если `pikachu` принял удар силой 100, а у него значение 
`defense` равное 40, то он получит лишь 60 урона.
* Покемон, число `hp` которого стало равным или меньшим 0, проигрывает
* При победе бойцовский клуб оглашает победителя, скачивая его изображение в папку проекта (Картинка покемона берется 
из объекта sprites с ключом `front_default`)

> *Это важно*. PokeApi требует, чтобы при любых запросах к его апи присутствовал заголовок `User-Agent`, в противном случае,
будет возвращён код ошибки `403` https://github.com/PokeAPI/pokeapi/issues/135
>

1. Добавьте зависимость `com.fasterxml.jackson.core.jackson-databind` - библиотека для маппинга JSON-файлов на объекты
2. Доработайте сущность `Pokemon`, для возможности маппинга JSON-документа. Если это необходимо, добавьте аннотации, конструктор
или дополнительные методы. Названия полей изменять **нельзя**
3. Имплементируйте класс `ObjectMapperFactory` - он обязан возвращать объект, с помощью которого мы будем совершать
маппинг
4. Имплементируйте `PokemonFethingService` и `PokemonFightingClubService`.
5. В методе main напишите демо, которое демонстрирует простой бой между покемонами `Pikachu` и `Slowpoke`.
6. *Имплементируйте юнит-тесты `PokemonFethingService` и `PokemonFightingClubService`. Для заглушки сервиса Poke-api
используйте [Wiremock](http://wiremock.org/docs/stubbing/). Постарайтесь добиться максимального коверейджа
7. Запушьте на гитхаб.
