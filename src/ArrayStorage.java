import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

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
        for (int i = 0; i < size - 1; ) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            } else {
                i++;
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
        for (int i = 0; i < size - 1; i++) {
            if (storage[i] != null) {
                resumesWithoutNull[i] = storage[i];
            }
        }
        return resumesWithoutNull;
    }

    int size() {
        return size;
    }
}
