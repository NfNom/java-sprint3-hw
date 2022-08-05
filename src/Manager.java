import java.util.ArrayList;
import java.util.HashMap;

public class Manager {
    private int idMap; //номер (id) задачи
    private ArrayList<Integer> listIdEpic = new ArrayList<>();// список id Epic`ов

    HashMap<Integer, Task> taskMap = new HashMap<>();
    HashMap<Integer, SubTask> subTaskMap = new HashMap<>();
    HashMap<Integer, Epic> epicMap = new HashMap<>();

    // 2.4 метод создания
    public void create(Object obj) {
        Task task = new Task();
        SubTask subTask = new SubTask();
        Epic epic = new Epic();

        if (obj.getClass() == task.getClas()) { //создание Task
            idMap++;
            task = (Task) obj;
            taskMap.put(idMap, task);
        } else if (obj.getClass() == subTask.getClas()) {//создание SubTask
            idMap++;
            subTask = (SubTask) obj;
            if (listIdEpic.contains(subTask.idEpic)) {//проверка существования id Epic
                subTaskMap.put(idMap, subTask);
            } else {
                System.out.println("Epic`а с id " + subTask.idEpic + " не существует! " +
                        "Необходимо указать корректный id Epic`а, " + subTask.name + " не создан");
            }
        } else {//создание Epic
            idMap++;
            epic = (Epic) obj;
            listIdEpic.add(idMap);
            epicMap.put(idMap, epic);
        }
    }
    //2.5 обновление задач

    public void updateTask(Object obj, int id, String status) {

        if (taskMap.containsKey(id)) {
            Task task = (Task) obj;
            task.status = status;
            taskMap.put(id, task);
        } else if (subTaskMap.containsKey(id)) {
            //taskMap.get(id)
            SubTask subTask = (SubTask) obj;
            subTask.status = status;
            subTaskMap.put(id, subTask);
            if (status.equals("inProgress")) {
                epicMap.get(subTask.idEpic).status = "inProgress";
            } else if (status.equals("Done")) {
                boolean isCheck = true;
                for (Integer taskId : subTaskMap.keySet()) {
                    SubTask subTask1 = subTaskMap.get(taskId);
                    if (subTask1.idEpic == id) {
                        if (subTask.status != "Done") {
                            isCheck = false;
                            return;
                        }
                    }
                }
                if (isCheck) {
                    epicMap.get(subTask.idEpic).status = "Done";
                }
            }
        } else if (epicMap.containsKey(id)) {
            Epic epic = (Epic) obj;
            taskMap.put(id, epic);
        } else {
            System.out.println("Задача с ID - " + id + "не найдена");
        }

    }


    //2.1 Получение списка всех задач.

    public void printAllTask() {
        for (Integer id : taskMap.keySet()) {
            /* просьба подсказать, немогу сообразить как этот цикл расписывается через fori */
            System.out.println("ID - " + id + " " + taskMap.get(id));
        }
        for (Integer id : subTaskMap.keySet()) {
            System.out.println("ID - " + id + " " + subTaskMap.get(id));
        }
        for (Integer id : epicMap.keySet()) {
            System.out.println("ID - " + id + " " + epicMap.get(id));
        }
    }

    //2.2 Удаление всех задач
    public void clearAllTask() {
        taskMap.clear();
        subTaskMap.clear();
        epicMap.clear();
        listIdEpic.clear();
        System.out.println("Все задачи удалены");
        System.out.println(taskMap + "\n" + subTaskMap + "\n" + epicMap);
    }

    //2.3 получение по идентификатору
    public void printById(int id) {
        if (taskMap.containsKey(id)) {
            System.out.println("Задача с ID  " + id + " это - " + taskMap.get(id));
        } else if (subTaskMap.containsKey(id)) {
            System.out.println("Задача с ID " + id + " это - " + subTaskMap.get(id));
        } else if (epicMap.containsKey(id)) {
            System.out.println("Задача с ID " + id + " это - " + epicMap.get(id));
        } else {
            System.out.println("Задача с ID " + id + "не найдена");
        }
    }

    //2.6 Удаление по идентификатору
    public void clearById(int id) {
        if (taskMap.containsKey(id)) { //// проверяем id в коллекции taskMap
            System.out.println("Задача с ID - " + id + " удалена");
            taskMap.remove(id);
        } else if (subTaskMap.containsKey(id)) { // проверяем id в коллекции subTaskMap
            System.out.println("Задача с ID - " + id + " удалена");
            subTaskMap.remove(id);
        } else if (epicMap.containsKey(id)) { // проверяем id в коллекции epicMap
            System.out.println("Задача с ID - " + id + " удалена");
            epicMap.remove(id);
            System.out.println(listIdEpic);
            listIdEpic.remove((Integer) id);
            System.out.println(listIdEpic);
            ArrayList<Integer> listIdSubTask = new ArrayList<>(); // создаем список из id subTask связанных с Epic
            for (Integer taskId : subTaskMap.keySet()) { // ищем subTask которые связаны в удаляемым Epic
                if (subTaskMap.get(taskId).idEpic == id) {
                    System.out.println("Задача с ID - " + id + " Epic-здача имеет подзадачу с ID - " + taskId + " которая тоже подлежит удалению");
                    listIdSubTask.add(taskId);
                }
            }
            for (int i = 0; i < listIdSubTask.size(); i++) {
                subTaskMap.remove(listIdSubTask.get(i));
            }

            subTaskMap.remove(4);
        } else {
            System.out.println("Задача с ID - " + id + "не найдена");
        }
    }

    // 3.1 получение списка всех подзадач определённого эпика
    public void printingEpicSubtask(int id) {
        if (listIdEpic.contains(id)) {
            System.out.println("Epic с ID - " + id);
            for (Integer taskId : subTaskMap.keySet()) { // шагаем по всем sabTask
                if (subTaskMap.get(taskId).idEpic == id) { // проверяем совподения
                    System.out.println(" имеет SubTask с ID - " + taskId);
                }
            }
        } else {
            System.out.println("Epic с ID - " + id + "не существует");
        }
    }


}



