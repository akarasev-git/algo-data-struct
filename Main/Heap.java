package Main;

import java.util.ArrayList;
import java.util.List;

//Желаетельно переписать так, чтобы функции требовали не срезы, а список и индексы!!!

//Стуктура данных Куча
//Является отсортированным (родитель всегда меньше, чем потомок, но разные ветки междду собой не сортируются) 
//в порядке возрастания двоичным деревом
//Т.к. дерево ДВОИЧНОЕ, то его легко хранить в виде массива, где элементы с индексами i * 2 + 1 и i * 2 + 2 - дети,
//а (i - 1) / 2 - родитель
//У структуры данных есть метода insert - добавляет новый элемент в дерево и перестраивает его (сортирует),
//getMin - находит минимум дерева (в случае, когда дерево отсортировано в порядке возрастания - корень)
//removeMin - удаляет минимум (корень) и перестраивает дерево
//Статический heapSort сортирует список методом Сортировки Кучей
public class Heap {

    public ArrayList<Integer> heapArray = new ArrayList<Integer>();

    //insert
    public void insert(int value){
        heapArray.add(value);
        Heap.siftUp(heapArray);
    }

    //get_min
    public int getMin(){
        return heapArray.get(0);
    }

    //remove_min
    public void removeMin(){
        heapArray.set(0, heapArray.getLast());
        heapArray.remove(heapArray.size() - 1);
        Heap.siftDown(heapArray);
    }


    //sift_up
    //Функцция перестроения ВВЕРХ
    //Если выбранный элемент меньше родителя, они заменяются
    //Перестроение продолжается, пока родитель не окажется меньше
    //Перестроение не влияет на соседнюю ветку, т.к. если потомок меньше родителя и родитель заведомо меньше
    //элементов соседнего дерева, то этот элемент будет ЕЩЁ меньше их
    private static void siftUp(List<Integer> list){
        int i = list.size() - 1;
        while(i > 0 && list.get(i) < list.get((i - 1)/ 2)){
            int temp = list.get(i);
            list.set(i, list.get((i - 1)/ 2));
            list.set((i - 1)/ 2, temp);
            i = (i - 1)/ 2;
        }
    }

    //sift_down
    //Функция перестроения ВНИЗ, если больший элемент оказался над меньшим
    //Проверяется, если ли у элемента хотя бы один потомок
    //Если потомка 2 выбирается наименьший
    //Больший элемент меняется  с выбранным
    //Перестроение продолжается, пока у элемента не окажется потомков, либо пока они не окажутся больше
    private static void siftDown(List<Integer> list){        
        int i = 0, j = 0;
        while(i * 2 + 1 < list.size() || i * 2 + 2 < list.size()){

            if(i * 2 + 2 < list.size() && list.get(i * 2 + 1) > list.get(i * 2 + 2)){
                j = i * 2 + 2;
            }else{
                j = i * 2 + 1;
            }

            if(list.get(j) > list.get(i)){
                break;

            }else{
                int temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
                i = j; 
            }
        }
    }

    //heapSort обладает сложностью nlogn и практически не требует дополнительной памяти
    //Сначала полученный список динамически перестраивается в структуру данных куча прямо на этом списке
    //Затем минимальный элемент отправляется в конец, а весь список без него вновь перестраивается так,
    //чтобы корень являлся минимальным элементом
    public static List<Integer> heapSort(List<Integer> list){

        //heapify
        //Можно делать через siftUp, но снизу у дерева больше элементов и у каждого путь длинной максимум в log(n)
        //Если же делать с помощью siftDown, то маскимальный путь длиной log(n) может быть только у корня дерева
        //Для реализации этого нужно идти снизу вверх и делать siftDown для кажого из поддеревьев
        for(int i = list.size() - 1; i >= 0; i--){
            Heap.siftDown(list.subList(i, list.size()));
        }

        //В полученном дереве минимальный элемент верхний, его заменяем на последний элемент списка
        //Для массива без последнего элемента делаем siftDown для того, чтобы корень снова стал минимальным элементом
        //Продолжаем выполнять алгоритм для второго и предпоследнего элементов, постепенно сходимся к центру
        for(int i = 0; i < list.size(); i++){
            int temp = list.get(0);
            list.set(0, list.get(list.size() - i - 1));
            list.set(list.size() - i - 1, temp);
            Heap.siftDown(list.subList(0, list.size()- i - 1));
        }
        return list;
    }

    public void print(){
        for(int i = 0; i < heapArray.size(); i++){
            System.out.print(heapArray.get((i)));
            if(i != heapArray.size() - 1){
                System.out.print(" - ");
            }
        }
    }

}
