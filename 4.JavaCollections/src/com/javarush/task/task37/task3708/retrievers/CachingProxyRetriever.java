package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever {

    OriginalRetriever originalRetriever;
    LRUCache<Long, Object> cache;

    public CachingProxyRetriever(Storage storage) {
        this.originalRetriever = new OriginalRetriever(storage);
        this.cache = new LRUCache(5);
    }

    @Override
    public Object retrieve(long id) {
        Object obj = cache.find(id);
        if (obj == null) {
            obj = originalRetriever.retrieve(id);
            cache.set(id, obj);
        }
        return obj;
    }
}
