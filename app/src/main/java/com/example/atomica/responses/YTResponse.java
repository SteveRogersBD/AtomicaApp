package com.example.atomica.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class YTResponse {
    public SearchMetadata search_metadata;
    public SearchParameters search_parameters;
    public SearchInformation search_information;
    public ArrayList<ChannelResult> channel_results;
    public ArrayList<LatestFromStarWar> latest_from_star_wars;
    public ArrayList<VideoResult> video_results;
    public ArrayList<ChannelsNewToYou> channels_new_to_you;
    public Pagination pagination;
    public SerpapiPagination serpapi_pagination;
    public class Channel{
        public String name;
        public String link;
        public boolean verified;
        public String thumbnail;
    }

    public class ChannelResult{
        public int position_on_page;
        public String title;
        public String link;
        public boolean verified;
        public String handle;
        public int subscribers;
        public String description;
        public String thumbnail;
    }

    public class ChannelsNewToYou{
        public Object position_on_page;
        public String title;
        public String link;
        public String serpapi_link;
        public Channel channel;
        public String published_date;
        public int views;
        public String length;
        public String description;
        public ArrayList<String> extensions;
        public Thumbnail thumbnail;
    }

    public class LatestFromStarWar{
        public Object position_on_page;
        public String title;
        public String link;
        public String serpapi_link;
        public Channel channel;
        public String published_date;
        public int views;
        public String length;
        public String description;
        public ArrayList<String> extensions;
        public Thumbnail thumbnail;
    }

    public class Pagination{
        public String current;
        public String next;
        public String next_page_token;
    }
    public class SearchInformation{
        public int total_results;
        public String video_results_state;
    }

    public class SearchMetadata{
        public String id;
        public String status;
        public String json_endpoint;
        public String created_at;
        public String processed_at;
        public String youtube_url;
        public String raw_html_file;
        public double total_time_taken;
    }

    public class SearchParameters{
        public String engine;
        public String search_query;
    }

    public class SerpapiPagination{
        public String current;
        public String next;
        public String next_page_token;
    }

    public class Thumbnail{
        @SerializedName("static")
        public String mystatic;
    }

    public class VideoResult{
        public int position_on_page;
        public String title;
        public String link;
        public String serpapi_link;
        public Channel channel;
        public String published_date;
        public int views;
        public String length;
        public String description;
        public ArrayList<String> extensions;
        public Thumbnail thumbnail;
    }
}
