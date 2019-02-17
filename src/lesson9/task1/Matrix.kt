@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson9.task1

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E

    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)

    operator fun set(cell: Cell, value: E)
}

/**
 * Простая
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> =
        if (height <= 0 || width <= 0)
            throw IllegalArgumentException()
        else MatrixImpl(e, height, width)


/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(private val type: E, override val height: Int, override val width: Int) : Matrix<E> {
    private val matrix = MutableList(height) { MutableList(width) { type } }

    override fun get(row: Int, column: Int): E = matrix[row][column]

    override fun get(cell: Cell): E = matrix[cell.row][cell.column]

    override fun set(row: Int, column: Int, value: E) {
        matrix[row][column] = value
    }

    override fun set(cell: Cell, value: E) {
        matrix[cell.row][cell.column] = value
    }

    override fun equals(other: Any?): Boolean {
        if (other is Matrix<*>) {
            for (i in 0 until height) {
                for (j in 0 until width)
                    if (matrix[i][j] != other[i, j])
                        return false
            }
            return true
        }
        return false
    }


    override fun toString(): String {
        var str = StringBuilder()
        for (i in 0 until height) {
            for (j in 0 until width)
                str.append(matrix[i][j]).append(" ")
            str.append("\n")
        }
        return str.toString()
    }
}

