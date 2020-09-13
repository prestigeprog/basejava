import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;
    int inc;

    void clear() {
        for (int i = 0; i < size - 1; i++) {
            if (storage[i] != null) {
                storage[i] = null;
            }
        }
    }

    void save(Resume r) {
        if (storage[size] == null) {
            storage[size] = r;
            size++;
        }
    }

    Resume get(String uuid) {
        for (inc = 0; inc < size - 1; ) {
            if (storage[inc].uuid.equals(uuid)) {
                return storage[inc];
            } else {
                inc++;
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size - 1; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumesWithoutNull = new Resume[storage.length];
        for (int i = 0; i < size - 1; ) {
            if (storage[i] != null) {
                resumesWithoutNull[i] = storage[i];
                i++;
            } else {
                i++;
            }
        }
        return resumesWithoutNull;
    }

    int size() {
        return size;
    }
}
