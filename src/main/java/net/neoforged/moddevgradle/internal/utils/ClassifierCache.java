package net.neoforged.moddevgradle.internal.utils;

import org.gradle.api.artifacts.PublishArtifact;
import org.gradle.api.artifacts.result.ResolvedArtifactResult;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ClassifierCache {
    public static final ClassifierCache INSTANCE = new ClassifierCache();
    private final Map<String, String> classifiers = new HashMap<>();

    public void cache(PublishArtifact artifact) {
        classifiers.put(parseKey(artifact.getFile()), artifact.getClassifier());
    }

    public String query(ResolvedArtifactResult artifact) {
        return classifiers.get(parseKey(artifact.getFile()));
    }

    public void clear() {
        classifiers.clear();
    }

    private String parseKey(File file) {
        return file.toPath().toAbsolutePath().normalize().toString();
    }
}
