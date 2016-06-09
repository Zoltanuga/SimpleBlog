package by.it.util;


public enum SortMode {
    SORT_BY_HEADER {
        @Override
        public String obtain() {
            return "header";
        }
    },
    SORT_BY_DATE {
        @Override
        public String obtain() {
            return "date";
        }
    };
   public abstract  String obtain();
}
