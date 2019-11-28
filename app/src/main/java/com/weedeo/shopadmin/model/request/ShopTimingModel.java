package com.weedeo.shopadmin.model.request;

import java.util.List;

public class ShopTimingModel {

    /**
     * shop_id : 5dc9335fd6539200078473d6
     * active_times : {"monday":{"is_checked":true,"timing":[{"id":1,"start_time":"time","end_time":"time"},{"id":2,"start_time":"time","end_time":"time"}]},"tuesday":{"is_checked":true,"timing":[{"id":1,"start_time":"time","end_time":"time"},{"id":2,"start_time":"time","end_time":"time"}]},"wednesday":{"is_checked":true,"timing":[{"id":1,"start_time":"time","end_time":"time"},{"id":2,"start_time":"time","end_time":"time"}]},"thursday":{"is_checked":true,"timing":[{"id":1,"start_time":"time","end_time":"time"},{"id":2,"start_time":"time","end_time":"time"}]},"friday":{"is_checked":true,"timing":[{"id":1,"start_time":"time","end_time":"time"},{"id":2,"start_time":"time","end_time":"time"}]},"saturday":{"is_checked":true,"timing":[{"id":1,"start_time":"time","end_time":"time"},{"id":2,"start_time":"time","end_time":"time"}]},"sunday":{"is_checked":true,"timing":[{"id":1,"start_time":"time","end_time":"time"},{"id":2,"start_time":"time","end_time":"time"}]}}
     */

    private String shop_id;
    private ActiveTimesBean active_times;

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public ActiveTimesBean getActive_times() {
        return active_times;
    }

    public void setActive_times(ActiveTimesBean active_times) {
        this.active_times = active_times;
    }

    public static class ActiveTimesBean {
        /**
         * monday : {"is_checked":true,"timing":[{"id":1,"start_time":"time","end_time":"time"},{"id":2,"start_time":"time","end_time":"time"}]}
         * tuesday : {"is_checked":true,"timing":[{"id":1,"start_time":"time","end_time":"time"},{"id":2,"start_time":"time","end_time":"time"}]}
         * wednesday : {"is_checked":true,"timing":[{"id":1,"start_time":"time","end_time":"time"},{"id":2,"start_time":"time","end_time":"time"}]}
         * thursday : {"is_checked":true,"timing":[{"id":1,"start_time":"time","end_time":"time"},{"id":2,"start_time":"time","end_time":"time"}]}
         * friday : {"is_checked":true,"timing":[{"id":1,"start_time":"time","end_time":"time"},{"id":2,"start_time":"time","end_time":"time"}]}
         * saturday : {"is_checked":true,"timing":[{"id":1,"start_time":"time","end_time":"time"},{"id":2,"start_time":"time","end_time":"time"}]}
         * sunday : {"is_checked":true,"timing":[{"id":1,"start_time":"time","end_time":"time"},{"id":2,"start_time":"time","end_time":"time"}]}
         */

        private MondayBean monday;
        private TuesdayBean tuesday;
        private WednesdayBean wednesday;
        private ThursdayBean thursday;
        private FridayBean friday;
        private SaturdayBean saturday;
        private SundayBean sunday;

        public MondayBean getMonday() {
            return monday;
        }

        public void setMonday(MondayBean monday) {
            this.monday = monday;
        }

        public TuesdayBean getTuesday() {
            return tuesday;
        }

        public void setTuesday(TuesdayBean tuesday) {
            this.tuesday = tuesday;
        }

        public WednesdayBean getWednesday() {
            return wednesday;
        }

        public void setWednesday(WednesdayBean wednesday) {
            this.wednesday = wednesday;
        }

        public ThursdayBean getThursday() {
            return thursday;
        }

        public void setThursday(ThursdayBean thursday) {
            this.thursday = thursday;
        }

        public FridayBean getFriday() {
            return friday;
        }

        public void setFriday(FridayBean friday) {
            this.friday = friday;
        }

        public SaturdayBean getSaturday() {
            return saturday;
        }

        public void setSaturday(SaturdayBean saturday) {
            this.saturday = saturday;
        }

        public SundayBean getSunday() {
            return sunday;
        }

        public void setSunday(SundayBean sunday) {
            this.sunday = sunday;
        }

        public static class MondayBean {
            /**
             * is_checked : true
             * timing : [{"id":1,"start_time":"time","end_time":"time"},{"id":2,"start_time":"time","end_time":"time"}]
             */

            private boolean is_checked;
            private List<TimingBean> timing;

            public boolean isIs_checked() {
                return is_checked;
            }

            public void setIs_checked(boolean is_checked) {
                this.is_checked = is_checked;
            }

            public List<TimingBean> getTiming() {
                return timing;
            }

            public void setTiming(List<TimingBean> timing) {
                this.timing = timing;
            }

            public static class TimingBean {
                /**
                 * id : 1
                 * start_time : time
                 * end_time : time
                 */

                private int id;
                private String start_time;
                private String end_time;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getStart_time() {
                    return start_time;
                }

                public void setStart_time(String start_time) {
                    this.start_time = start_time;
                }

                public String getEnd_time() {
                    return end_time;
                }

                public void setEnd_time(String end_time) {
                    this.end_time = end_time;
                }
            }
        }

        public static class TuesdayBean {
            /**
             * is_checked : true
             * timing : [{"id":1,"start_time":"time","end_time":"time"},{"id":2,"start_time":"time","end_time":"time"}]
             */

            private boolean is_checked;
            private List<TimingBeanX> timing;

            public boolean isIs_checked() {
                return is_checked;
            }

            public void setIs_checked(boolean is_checked) {
                this.is_checked = is_checked;
            }

            public List<TimingBeanX> getTiming() {
                return timing;
            }

            public void setTiming(List<TimingBeanX> timing) {
                this.timing = timing;
            }

            public static class TimingBeanX {
                /**
                 * id : 1
                 * start_time : time
                 * end_time : time
                 */

                private int id;
                private String start_time;
                private String end_time;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getStart_time() {
                    return start_time;
                }

                public void setStart_time(String start_time) {
                    this.start_time = start_time;
                }

                public String getEnd_time() {
                    return end_time;
                }

                public void setEnd_time(String end_time) {
                    this.end_time = end_time;
                }
            }
        }

        public static class WednesdayBean {
            /**
             * is_checked : true
             * timing : [{"id":1,"start_time":"time","end_time":"time"},{"id":2,"start_time":"time","end_time":"time"}]
             */

            private boolean is_checked;
            private List<TimingBeanXX> timing;

            public boolean isIs_checked() {
                return is_checked;
            }

            public void setIs_checked(boolean is_checked) {
                this.is_checked = is_checked;
            }

            public List<TimingBeanXX> getTiming() {
                return timing;
            }

            public void setTiming(List<TimingBeanXX> timing) {
                this.timing = timing;
            }

            public static class TimingBeanXX {
                /**
                 * id : 1
                 * start_time : time
                 * end_time : time
                 */

                private int id;
                private String start_time;
                private String end_time;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getStart_time() {
                    return start_time;
                }

                public void setStart_time(String start_time) {
                    this.start_time = start_time;
                }

                public String getEnd_time() {
                    return end_time;
                }

                public void setEnd_time(String end_time) {
                    this.end_time = end_time;
                }
            }
        }

        public static class ThursdayBean {
            /**
             * is_checked : true
             * timing : [{"id":1,"start_time":"time","end_time":"time"},{"id":2,"start_time":"time","end_time":"time"}]
             */

            private boolean is_checked;
            private List<TimingBeanXXX> timing;

            public boolean isIs_checked() {
                return is_checked;
            }

            public void setIs_checked(boolean is_checked) {
                this.is_checked = is_checked;
            }

            public List<TimingBeanXXX> getTiming() {
                return timing;
            }

            public void setTiming(List<TimingBeanXXX> timing) {
                this.timing = timing;
            }

            public static class TimingBeanXXX {
                /**
                 * id : 1
                 * start_time : time
                 * end_time : time
                 */

                private int id;
                private String start_time;
                private String end_time;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getStart_time() {
                    return start_time;
                }

                public void setStart_time(String start_time) {
                    this.start_time = start_time;
                }

                public String getEnd_time() {
                    return end_time;
                }

                public void setEnd_time(String end_time) {
                    this.end_time = end_time;
                }
            }
        }

        public static class FridayBean {
            /**
             * is_checked : true
             * timing : [{"id":1,"start_time":"time","end_time":"time"},{"id":2,"start_time":"time","end_time":"time"}]
             */

            private boolean is_checked;
            private List<TimingBeanXXXX> timing;

            public boolean isIs_checked() {
                return is_checked;
            }

            public void setIs_checked(boolean is_checked) {
                this.is_checked = is_checked;
            }

            public List<TimingBeanXXXX> getTiming() {
                return timing;
            }

            public void setTiming(List<TimingBeanXXXX> timing) {
                this.timing = timing;
            }

            public static class TimingBeanXXXX {
                /**
                 * id : 1
                 * start_time : time
                 * end_time : time
                 */

                private int id;
                private String start_time;
                private String end_time;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getStart_time() {
                    return start_time;
                }

                public void setStart_time(String start_time) {
                    this.start_time = start_time;
                }

                public String getEnd_time() {
                    return end_time;
                }

                public void setEnd_time(String end_time) {
                    this.end_time = end_time;
                }
            }
        }

        public static class SaturdayBean {
            /**
             * is_checked : true
             * timing : [{"id":1,"start_time":"time","end_time":"time"},{"id":2,"start_time":"time","end_time":"time"}]
             */

            private boolean is_checked;
            private List<TimingBeanXXXXX> timing;

            public boolean isIs_checked() {
                return is_checked;
            }

            public void setIs_checked(boolean is_checked) {
                this.is_checked = is_checked;
            }

            public List<TimingBeanXXXXX> getTiming() {
                return timing;
            }

            public void setTiming(List<TimingBeanXXXXX> timing) {
                this.timing = timing;
            }

            public static class TimingBeanXXXXX {
                /**
                 * id : 1
                 * start_time : time
                 * end_time : time
                 */

                private int id;
                private String start_time;
                private String end_time;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getStart_time() {
                    return start_time;
                }

                public void setStart_time(String start_time) {
                    this.start_time = start_time;
                }

                public String getEnd_time() {
                    return end_time;
                }

                public void setEnd_time(String end_time) {
                    this.end_time = end_time;
                }
            }
        }

        public static class SundayBean {
            /**
             * is_checked : true
             * timing : [{"id":1,"start_time":"time","end_time":"time"},{"id":2,"start_time":"time","end_time":"time"}]
             */

            private boolean is_checked;
            private List<TimingBeanXXXXXX> timing;

            public boolean isIs_checked() {
                return is_checked;
            }

            public void setIs_checked(boolean is_checked) {
                this.is_checked = is_checked;
            }

            public List<TimingBeanXXXXXX> getTiming() {
                return timing;
            }

            public void setTiming(List<TimingBeanXXXXXX> timing) {
                this.timing = timing;
            }

            public static class TimingBeanXXXXXX {
                /**
                 * id : 1
                 * start_time : time
                 * end_time : time
                 */

                private int id;
                private String start_time;
                private String end_time;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getStart_time() {
                    return start_time;
                }

                public void setStart_time(String start_time) {
                    this.start_time = start_time;
                }

                public String getEnd_time() {
                    return end_time;
                }

                public void setEnd_time(String end_time) {
                    this.end_time = end_time;
                }
            }
        }
    }
}
