// Copyright 2016 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.verygood.security.larky.modules.path;

import com.google.common.collect.ImmutableList;

import net.starlark.java.eval.Printer;

import java.io.IOException;
import java.nio.file.Path;

/**
 * A Path object to be used into Starlark remote repository.
 *
 * <p>This path object enable non-hermetic operations from Starlark and should not be returned by
 * something other than a StarlarkRepositoryContext.
 */
final class StarlarkPath implements RepositoryPathApi<StarlarkPath> {
  private final Path path;

  StarlarkPath(Path path) {
    this.path = path;
  }

  Path getPath() {
    return path;
  }

  @Override
  public boolean isImmutable() {
    return true; // immutable and Starlark-hashable
  }

  @Override
  public boolean equals(Object obj) {
    return (obj instanceof StarlarkPath) &&  path.equals(((StarlarkPath) obj).path);
  }

  @Override
  public int hashCode() {
    return path.hashCode();
  }

  @Override
  public String getBasename() { return String.valueOf(path.getFileName()); }

  @Override
  public ImmutableList<StarlarkPath> readdir() throws IOException {
    ImmutableList.Builder<StarlarkPath> builder = ImmutableList.builder();
//    for (Path p : path.()) {
//      builder.add(new StarlarkPath(p));
//    }
    return builder.build();
  }

  @Override
  public StarlarkPath getDirname() {
    Path parentPath = path.getParent();
    return parentPath == null ? null : new StarlarkPath(parentPath);
  }

  @Override
  public StarlarkPath getChild(String childPath) {
//    return new StarlarkPath(path.getChild(childPath));
    return null;
  }

  @Override
  public boolean exists() {
    return false;
//    return path.exists();
  }

  @Override
  public StarlarkPath realpath() throws IOException {
    return null;
//    return new StarlarkPath(path.resolveSymbolicLinks());
  }

  @Override
  public String toString() {
    return path.toString();
  }

  @Override
  public void repr(Printer printer) {
    printer.append(toString());
  }
}
