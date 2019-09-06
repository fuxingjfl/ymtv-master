package com.lemon95.ymtv.dao;

import com.lemon95.ymtv.api.ApiManager;
import com.lemon95.ymtv.api.WXApiManager;
import com.lemon95.ymtv.bean.Conditions;
import com.lemon95.ymtv.bean.Favorite;
import com.lemon95.ymtv.bean.FavoritesBean;
import com.lemon95.ymtv.bean.FirstLettersSearch;
import com.lemon95.ymtv.bean.ForWechat;
import com.lemon95.ymtv.bean.GenresMovie;
import com.lemon95.ymtv.bean.GetOrder;
import com.lemon95.ymtv.bean.Movie;
import com.lemon95.ymtv.bean.MovieSources;
import com.lemon95.ymtv.bean.PersonalMovies;
import com.lemon95.ymtv.bean.SerialDitions;
import com.lemon95.ymtv.bean.UploadResult;
import com.lemon95.ymtv.bean.VideoSearchList;
import com.lemon95.ymtv.bean.VideoWatchHistory;
import com.lemon95.ymtv.bean.WatchHistories;
import com.lemon95.ymtv.bean.impl.IMovieBean;
import com.lemon95.ymtv.utils.LogUtils;

import java.util.List;

import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by WXT on 2016/7/18.
 */
public class MovieDao implements IMovieBean{


    private static final String TAG = "MovieDao";

    @Override
    public void getMovieDetails(String id, String userId, boolean isPersonal,final OnMovieDetailsListener movieDetailsListener) {
        ApiManager.getDetails(id,userId,isPersonal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Movie>() {

                    @Override
                    public void call(Movie movie) {
                        LogUtils.i(TAG, movie.getReturnMsg());
                        movieDetailsListener.onSuccess(movie);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        //获取版本失败
                        LogUtils.i(TAG, "获取影视类型失败");
                        movieDetailsListener.onFailure(throwable);
                    }
                });
    }

    @Override
    public void getMoviesByGenres(String genreIds, String vipLevel, String currenPage, String pageSize,final OnGenresMovieDetailsListener onVideoListener) {
        ApiManager.getMoviesByGenres(genreIds, vipLevel, currenPage, pageSize).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GenresMovie>() {

                    @Override
                    public void call(GenresMovie movie) {
                        LogUtils.i(TAG, movie.getReturnMsg());
                        onVideoListener.onSuccess(movie);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        //获取版本失败
                        LogUtils.i(TAG, "相应题材影视获取失败");
                        onVideoListener.onFailure(throwable);
                    }
                });
    }

    @Override
    public void getSerialsByGenres(String genreIds, String vipLevel, String currenPage, String pageSize, final OnGenresMovieDetailsListener onVideoListener) {
        ApiManager.getSerialsByGenres(genreIds, vipLevel, currenPage, pageSize).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GenresMovie>() {

                    @Override
                    public void call(GenresMovie movie) {
                        LogUtils.i(TAG, movie.getReturnMsg());
                        onVideoListener.onSuccess(movie);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        //获取版本失败
                        LogUtils.i(TAG, "相应题材影视获取失败");
                        onVideoListener.onFailure(throwable);
                    }
                });
    }

    @Override
    public void getCombQueryConditions(String type, final OnConditionsListener onVideoListener) {
        ApiManager.getCombQueryConditions(type).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Conditions>() {

                    @Override
                    public void call(Conditions conditions) {
                        LogUtils.i(TAG, conditions.getReturnMsg());
                        onVideoListener.onSuccess(conditions);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        //获取版本失败
                        LogUtils.i(TAG, "影视查询类型获取失败");
                        onVideoListener.onFailure(throwable);
                    }
                });
    }

    @Override
    public void getCombSearch(String areaId, String genreId, String groupId, String chargeMethod, String vipLevel, String year, String type, String currentPage, String pageSize, final OnVideoSearchListListener onVideoSearchListListener) {
        ApiManager.getCombSearch(areaId, genreId, groupId, chargeMethod, vipLevel, year, type, currentPage, pageSize).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<VideoSearchList>() {

                    @Override
                    public void call(VideoSearchList videoSearchList) {
                        LogUtils.i(TAG, videoSearchList.getReturnMsg());
                        onVideoSearchListListener.onSuccess(videoSearchList);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        //获取版本失败
                        LogUtils.i(TAG, "影视列表获取失败");
                        onVideoSearchListListener.onFailure(throwable);
                    }
                });
    }

    @Override
    public void getSerialDetail(String id, final OnSerialDitionListener onSerialDitionListener) {
        ApiManager.getSerialDetail(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<SerialDitions>() {

                    @Override
                    public void call(SerialDitions serialDitions) {
                        LogUtils.i(TAG, serialDitions.getReturnMsg());
                        onSerialDitionListener.onSuccess(serialDitions);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        LogUtils.i(TAG, "电视剧详情获取失败");
                        onSerialDitionListener.onFailure(throwable);
                    }
                });
    }

    /**
     * 解析电影
     * @param id
     * @param onMovieAnalysisListener
     */
    @Override
    public void getMovieAnalysis(String id, final OnMovieAnalysisListener onMovieAnalysisListener) {
        ApiManager.getMovieAnalysis(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<MovieSources>>() {

                    @Override
                    public void call(List<MovieSources> movieSources) {
                        onMovieAnalysisListener.onSuccess(movieSources);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        onMovieAnalysisListener.onFailure(throwable);
                    }
                });
    }

    @Override
    public void getSerialAnalysis(String id, final OnSerialAnalysisListener onMovieAnalysisListener) {
        ApiManager.getSerialAnalysis(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {

                    @Override
                    public void call(String movieSources) {
                        onMovieAnalysisListener.onSuccess(movieSources);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        onMovieAnalysisListener.onFailure(throwable);
                    }
                });
    }

    @Override
    public void addVideoWatchHistory(VideoWatchHistory videoWatchHistory, final OnUpdateListener onUpdateListener) {
        ApiManager.addVideoWatchHistory(videoWatchHistory).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<UploadResult>() {

                    @Override
                    public void call(UploadResult uploadResult) {
                        onUpdateListener.onSuccess(uploadResult);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        onUpdateListener.onFailure(throwable);
                    }
                });
    }

    @Override
    public void addFavorite(Favorite favorite, final OnUpdateListener onUpdateListener) {
        ApiManager.addFavorite(favorite).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<UploadResult>() {

                    @Override
                    public void call(UploadResult uploadResult) {
                        onUpdateListener.onSuccess(uploadResult);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        onUpdateListener.onFailure(throwable);
                    }
                });
    }

    @Override
    public void getFavorites(String mac, String userId,String currentPage,String pageSize, final OnFavoritesBeanListener onFavoritesBeanListener) {
        ApiManager.getFavorites(mac, userId,currentPage,pageSize).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<FavoritesBean>() {

                    @Override
                    public void call(FavoritesBean favoritesBean) {
                        onFavoritesBeanListener.onSuccess(favoritesBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        onFavoritesBeanListener.onFailure(throwable);
                    }
                });
    }

    @Override
    public void deleteFavorite(String id[], final OnUpdateListener onUpdateListener) {
        ApiManager.deleteFavorite(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<UploadResult>() {

                    @Override
                    public void call(UploadResult uploadResult) {
                        onUpdateListener.onSuccess(uploadResult);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        onUpdateListener.onFailure(throwable);
                    }
                });
    }

    @Override
    public void getWatchHistories(String currentPage, String pageSize, String mac, String userId, final OnWatchHistoriesListener onWatchHistoriesListener) {
        ApiManager.getWatchHistories(currentPage,pageSize,mac,userId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<WatchHistories>() {

                    @Override
                    public void call(WatchHistories watchHistories) {
                        onWatchHistoriesListener.onSuccess(watchHistories);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        onWatchHistoriesListener.onFailure(throwable);
                    }
                });
    }

    @Override
    public void deletePersonalHistories(String[] historyIds, final OnUpdateListener onUpdateListener) {
        ApiManager.deletePersonalHistories(historyIds).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<UploadResult>() {

                    @Override
                    public void call(UploadResult uploadResult) {
                        onUpdateListener.onSuccess(uploadResult);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        onUpdateListener.onFailure(throwable);
                    }
                });
    }

    @Override
    public void getPersonalMovies(String userId, String vipLevel, String currentPage, String pageSize, final OnPersonalMoviesListener onPersonalMoviesListener) {
        ApiManager.getPersonalMovies(userId, vipLevel, currentPage, pageSize).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<PersonalMovies>() {

                    @Override
                    public void call(PersonalMovies uploadResult) {
                        onPersonalMoviesListener.onSuccess(uploadResult);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        onPersonalMoviesListener.onFailure(throwable);
                    }
                });
    }

    @Override
    public void getForWechat(String userId, String chargemethod, String videoId, final OnForWechatListener onForWechatListener) {
        ApiManager.getForWechat(userId, chargemethod, videoId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ForWechat>() {

                    @Override
                    public void call(ForWechat forWechat) {
                        onForWechatListener.onSuccess(forWechat);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        onForWechatListener.onFailure(throwable);
                    }
                });
    }

    @Override
    public void unifiedorder(String xml, final OnUnifiedorderListener onUnifiedorderListener) {
       WXApiManager.unifiedorder(xml).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<ResponseBody>() {

            @Override
            public void call(ResponseBody unifiedorder) {
                onUnifiedorderListener.onSuccess(unifiedorder);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
                onUnifiedorderListener.onFailure(throwable);
            }
        });
    }

    @Override
    public void getFirstLettersSearch(String firstLetters, String currentPage, String pageSize, final OnFirstLettersSearchListener onFirstLettersSearchListener) {
        ApiManager.getFirstLettersSearch(firstLetters, currentPage, pageSize).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<FirstLettersSearch>() {

                    @Override
                    public void call(FirstLettersSearch firstLettersSearch) {
                        onFirstLettersSearchListener.onSuccess(firstLettersSearch);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        onFirstLettersSearchListener.onFailure(throwable);
                    }
                });
    }

    @Override
    public void getOrder(String order, final OnOrderListener onOrderListener) {
        ApiManager.getOrder(order).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GetOrder>() {

                    @Override
                    public void call(GetOrder order1) {
                        onOrderListener.onSuccess(order1);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        onOrderListener.onFailure(throwable);
                    }
                });
    }

    public interface OnMovieDetailsListener{
        void onSuccess(Movie movie);  //获取成功
        void onFailure(Throwable e);  //获取失败
    }

    public interface OnGenresMovieDetailsListener{
        void onSuccess(GenresMovie movie);  //获取成功
        void onFailure(Throwable e);  //获取失败
    }

    public interface OnConditionsListener{
        void onSuccess(Conditions movie);  //获取成功
        void onFailure(Throwable e);  //获取失败
    }

    public interface OnVideoSearchListListener{
        void onSuccess(VideoSearchList videoSearchList);  //获取成功
        void onFailure(Throwable e);  //获取失败
    }

    public interface OnSerialDitionListener{
        void onSuccess(SerialDitions serialDitions);  //获取成功
        void onFailure(Throwable e);  //获取失败
    }

    public interface OnMovieAnalysisListener{
        void onSuccess(List<MovieSources> movieSources);
        void onFailure(Throwable e);
    }

    public interface OnSerialAnalysisListener{
        void onSuccess(String movieSources);
        void onFailure(Throwable e);
    }

    public interface OnUpdateListener{
        void onSuccess(UploadResult uploadResult);
        void onFailure(Throwable e);
    }

    public interface OnFavoritesBeanListener{
        void onSuccess(FavoritesBean favoritesBean);
        void onFailure(Throwable e);
    }

    public interface OnWatchHistoriesListener{
        void onSuccess(WatchHistories watchHistories);
        void onFailure(Throwable e);
    }

    public interface OnPersonalMoviesListener{
        void onSuccess(PersonalMovies personalMovies);
        void onFailure(Throwable e);
    }

    public interface OnForWechatListener{
        void onSuccess(ForWechat forWechat);
        void onFailure(Throwable e);
    }

    public interface OnUnifiedorderListener{
        void onSuccess(ResponseBody unifiedorder);
        void onFailure(Throwable e);
    }

    public interface OnFirstLettersSearchListener{
        void onSuccess(FirstLettersSearch firstLettersSearch);
        void onFailure(Throwable e);
    }

    public interface OnOrderListener{
        void onSuccess(GetOrder getOrder);
        void onFailure(Throwable e);
    }



}
