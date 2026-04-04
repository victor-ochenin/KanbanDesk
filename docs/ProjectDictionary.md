Bundle - класс который позволяет передавать информацию между компонентами в андроид. Это контейнер с парой ключ знаение.

LayoutInflater - класс который преобразует XML‑разметку в обьекты View

RecyclerView - виджет для отображения больших списков данных с эффективной переработкой views. Вместо создания нового view для каждого элемента, переиспользует уже созданные через ViewHolder.

ViewHolder - паттерн и класс внутри адаптера, который хранит ссылки на элементы разметки item'а. Позволяет избежать повторных вызовов findViewById() при прокрутке списка.

LayoutManager - класс, который определяет как элементы располагаются в RecyclerView. LinearLayoutManager размещает элементы вертикально или горизонтально в виде списка.

CustomAdapter - кастомный адаптер наследующий RecyclerView.Adapter<CustomAdapter.ViewHolder>. Принимает List<TaskItem> и отображает каждый элемент через разметку task_item.xml.

DividerItemDecoration - утилита из androidx.recyclerview.widget, которая рисует разделительные линии между элементами RecyclerView.

ItemTouchHelper - утилита для реализации drag-and-drop (перетаскивания) и swipe (свайпа) элементов RecyclerView.