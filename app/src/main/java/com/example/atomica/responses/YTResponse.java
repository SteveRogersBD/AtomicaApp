package com.example.atomica.responses;

import java.util.ArrayList;

public class YTResponse {
    public ArrayList<Content> contents;
    public String cursorNext;
    public Object didYouMean;
    public int estimatedResults;
    public ArrayList<FilterGroup> filterGroups;
    public ArrayList<String> refinements;
    public class Author{
        public ArrayList<Avatar> avatar;
        public ArrayList<Badge> badges;
        public String canonicalBaseUrl;
        public String channelId;
        public String title;
    }

    public class Avatar{
        public int height;
        public String url;
        public int width;
    }

    public class Badge{
        public String text;
        public String type;
    }

    public class Content{
        public String type;
        public Video video;
    }

    public class Filter{
        public String cursorSelect;
        public String label;
        public boolean selected;
    }

    public class FilterGroup{
        public ArrayList<Filter> filters;
        public String title;
    }

    public class MovingThumbnail{
        public int height;
        public String url;
        public int width;
    }


    public class Stats{
        public int views;
    }

    public class Thumbnail{
        public int height;
        public String url;
        public int width;
    }

    public class Video{
        public Author author;
        public ArrayList<String> badges;
        public String descriptionSnippet;
        public boolean isLiveNow;
        public int lengthSeconds;
        public ArrayList<MovingThumbnail> movingThumbnails;
        public String publishedTimeText;
        public Stats stats;
        public ArrayList<Thumbnail> thumbnails;
        public String title;
        public String videoId;
    }

}
