package com.ternak.sapi.service;

import com.ternak.sapi.exception.BadRequestException;
import com.ternak.sapi.model.User;
import com.ternak.sapi.payload.PagedResponse;
import com.ternak.sapi.repository.UserRepository;
import com.ternak.sapi.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class UserService {

    private UserRepository userRepository = new UserRepository();

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    public PagedResponse<User> getAllUser(int page, int size) throws IOException {
        validatePageNumberAndSize(page, size);

        // Retrieve Polls
        List<User> userResponse = userRepository.findAll(size);


        return new PagedResponse<>(userResponse, userResponse.size(), "Successfully get data", 200);
    }

    public PagedResponse<User> getUserNotUsedAccount(int page, int size) throws IOException {
        validatePageNumberAndSize(page, size);

        // Retrieve Polls
        List<User> userResponse = userRepository.findUsersNotUsedInLectures(size);

        return new PagedResponse<>(userResponse, userResponse.size(), "Successfully get data", 200);
    }


    private void validatePageNumberAndSize(int page, int size) {
        if(page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if(size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }
}
