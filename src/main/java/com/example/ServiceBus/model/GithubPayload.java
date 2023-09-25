package com.example.ServiceBus.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GithubPayload {
    private String ref;
    private String before;
    private String after;
    private Repository repository;
    private Pusher pusher;
    private Sender sender;
    private boolean created;
    private boolean deleted;
    private boolean forced;
    private String base_ref;
    private String compare;
    private List<Commit> commits;
    private Commit head_commit;



    public String getRef() {
        return ref;
    }



    public void setRef(String ref) {
        this.ref = ref;
    }



    public String getBefore() {
        return before;
    }



    public void setBefore(String before) {
        this.before = before;
    }



    public String getAfter() {
        return after;
    }



    public void setAfter(String after) {
        this.after = after;
    }



    public Repository getRepository() {
        return repository;
    }



    public void setRepository(Repository repository) {
        this.repository = repository;
    }



    public Pusher getPusher() {
        return pusher;
    }



    public void setPusher(Pusher pusher) {
        this.pusher = pusher;
    }



    public Sender getSender() {
        return sender;
    }



    public void setSender(Sender sender) {
        this.sender = sender;
    }



    public boolean isCreated() {
        return created;
    }



    public void setCreated(boolean created) {
        this.created = created;
    }



    public boolean isDeleted() {
        return deleted;
    }



    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }



    public boolean isForced() {
        return forced;
    }



    public void setForced(boolean forced) {
        this.forced = forced;
    }



    public String getBase_ref() {
        return base_ref;
    }



    public void setBase_ref(String base_ref) {
        this.base_ref = base_ref;
    }



    public String getCompare() {
        return compare;
    }



    public void setCompare(String compare) {
        this.compare = compare;
    }



    public List<Commit> getCommits() {
        return commits;
    }



    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }



    public Commit getHead_commit() {
        return head_commit;
    }



    public void setHead_commit(Commit head_commit) {
        this.head_commit = head_commit;
    }



    public static class Repository {
        private String id;
        private String name;
        private String full_name;



        public String getId() {
            return id;
        }



        public void setId(String id) {
            this.id = id;
        }



        public String getName() {
            return name;
        }



        public void setName(String name) {
            this.name = name;
        }



        public String getFull_name() {
            return full_name;
        }



        public void setFull_name(String full_name) {
            this.full_name = full_name;
        }
    }



    public static class Pusher {
        private String name;
        private String email;



        public String getName() {
            return name;
        }



        public void setName(String name) {
            this.name = name;
        }



        public String getEmail() {
            return email;
        }



        public void setEmail(String email) {
            this.email = email;
        }
    }



    public static class Sender {
        private String login;
        private int id;



        public String getLogin() {
            return login;
        }



        public void setLogin(String login) {
            this.login = login;
        }



        public int getId() {
            return id;
        }



        public void setId(int id) {
            this.id = id;
        }
    }



    public static class Commit {
        private String id;
        private String tree_id;
        private boolean distinct;
        private String message;
        private String timestamp;
        private String url;
        private Author author;
        private Author committer;
        private List<String> added;
        private List<String> removed;
        private List<String> modified;



        public String getId() {
            return id;
        }



        public void setId(String id) {
            this.id = id;
        }



        public String getTree_id() {
            return tree_id;
        }



        public void setTree_id(String tree_id) {
            this.tree_id = tree_id;
        }



        public boolean isDistinct() {
            return distinct;
        }



        public void setDistinct(boolean distinct) {
            this.distinct = distinct;
        }



        public String getMessage() {
            return message;
        }



        public void setMessage(String message) {
            this.message = message;
        }



        public String getTimestamp() {
            return timestamp;
        }



        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }



        public String getUrl() {
            return url;
        }



        public void setUrl(String url) {
            this.url = url;
        }



        public Author getAuthor() {
            return author;
        }



        public void setAuthor(Author author) {
            this.author = author;
        }



        public Author getCommitter() {
            return committer;
        }



        public void setCommitter(Author committer) {
            this.committer = committer;
        }



        public List<String> getAdded() {
            return added;
        }



        public void setAdded(List<String> added) {
            this.added = added;
        }



        public List<String> getRemoved() {
            return removed;
        }



        public void setRemoved(List<String> removed) {
            this.removed = removed;
        }



        public List<String> getModified() {
            return modified;
        }



        public void setModified(List<String> modified) {
            this.modified = modified;
        }



        public static class Author {
            private String name;
            private String email;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }
        }
    }
}

