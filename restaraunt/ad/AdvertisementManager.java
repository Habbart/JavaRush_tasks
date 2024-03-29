package com.javarush.task.task27.task2712.ad;
import com.javarush.task.task27.task2712.*;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;
    
    public AdvertisementManager(int timeSeconds){
        this.timeSeconds = timeSeconds;
    }
    
    public void processVideos() throws NoVideoAvailableException{
        if(storage.list().isEmpty()){
                throw new NoVideoAvailableException();
        }
        
    }

    //создаем лист результатов
    //создаем временный лист для отбора
    //сортируем массив роликов
    //1.проверяем остались ли ролики в листе, если да - то продолжаем отбор, иначе отбор закончен
    //1.1 проверяем общую длину роликов < время заказа, то продолжаем, иначе отбор закончен
    // 2.Отбор:
    //2.1берем ролик с самой большой стоимостью в секунду
    //2.2.если длина ролика > время ожидания, то берем следующий ролик
    //2.3.иначе добавляем ролик в результат, вычеркиваем из листа
    // возвращаемся к пункту 1.
    //3.После отбора:
    // 3.1копируем результат в лист с результатами из листа с отборами
    // 3.2обнуляем лист отбора
    // 3.3можно сделать шафл листа с видео или начать с конца, или начать со второго элемента
    // 3.4повторить цикл с отбором
    // 3.5сравнить лист отбора и результата
    //3.5.1 если одинаковая сумма денег в обоих листах
    // 3.5.1.1 - сравнить по общему времени
    // 3.5.1.1.1 - если время одинаковое, то выбрать с наименьшим количеством роликов


//    2.2. Подобрать список видео из доступных, просмотр которых обеспечивает максимальную выгоду. (Пока делать не нужно, сделаем позже).

//      2.4. Отобразить все рекламные ролики, отобранные для показа, в порядке уменьшения стоимости показа одного рекламного ролика в копейках. Вторичная сортировка - по увеличению стоимости показа одной секунды рекламного ролика в тысячных частях копейки.
//    Используй метод Collections.sort
//
//    Пример для заказа [WATER]:
//    First Video is displaying... 50, 277
//    где First Video - название рекламного ролика
//    где 50 - стоимость показа одного рекламного ролика в копейках
//    где 277 - стоимость показа одной секунды рекламного ролика в тысячных частях копейки (равно 0.277 коп)
//    Используй методы из класса Advertisement.
//            (Этот пункт тоже пока делать не нужно, сделаем позже).
}
