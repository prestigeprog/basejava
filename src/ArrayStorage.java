/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        for (int i = 0; i < size - 1; i++) {
            if (storage[i] != null) {
                storage[i] = new Resume();
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
        Resume r = new Resume();
        for (int i = 0; i < size - 1; i++) {
            if (storage[i].uuid.equals(uuid)) {
                r = storage[i];
                break;
            }
        }
        return r;
    }

    void delete(String uuid) {
        for (int i = 0; i < size - 1; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = new Resume();
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] ResumesWithoutNull = new Resume[storage.length];
        for (int i = 0; i < size - 1; ) {
            if (storage[i] != null) {
                ResumesWithoutNull[i] = storage[i];
                i++;
            } else {
                i++;
            }
        }
        return ResumesWithoutNull;
    }

    int size() {
        while(true){
            if(storage[size] !=null){
                size++;
                continue;
            } else {
                break;
            }
        }
        return size;
    }
}
