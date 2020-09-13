/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            if (storage[i] != null) {
                storage[i] = null;
                size = 0;
            }
        }
    }

    void save(Resume r) {
            storage[size] = r;
            size++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = null;
                size--;
            }
        }
        Resume[] resumesWithoutNull = new Resume[storage.length];
        for (int i = 0; i < size; i++) {
            if (storage[i] != null) {
                resumesWithoutNull[i] = storage[i];
            }
        }
        storage = resumesWithoutNull;
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return storage;
    }

    int size() {
        return size;
    }
}
