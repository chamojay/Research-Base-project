/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;

/**
 * Simple JavaBean domain object representing a visit.
 *
 * @author Ken Krebs
 */
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {

    /**
     * Holds value of property date.
     */
    @Column(name = "visit_date", columnDefinition = "DATE")
    private LocalDate date;

    /**
     * Holds value of property description.
     */
    @NotEmpty
    @Column(name = "description")
    private String description;

    /**
     * Holds value of property pet.
     */
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    /**
     * Indicates whether the visit has been cancelled.
     */
    @Column(name = "cancelled", nullable = false)
    private Boolean cancelled = false;

    /**
     * Reason recorded when the visit was cancelled.
     */
    @Column(name = "cancellation_reason")
    private String cancellationReason;


    /**
     * Creates a new instance of Visit for the current date
     */
    public Visit() {
        this.date = LocalDate.now();
        this.cancelled = false;
    }


    /**
     * Getter for property date.
     *
     * @return Value of property date.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Setter for property date.
     *
     * @param date New value of property date.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Getter for property description.
     *
     * @return Value of property description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for property description.
     *
     * @param description New value of property description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for property pet.
     *
     * @return Value of property pet.
     */
    public Pet getPet() {
        return this.pet;
    }

    /**
     * Setter for property pet.
     *
     * @param pet New value of property pet.
     */
    public void setPet(Pet pet) {
        this.pet = pet;
    }

    /**
     * Check if visit is cancelled.
     *
     * @return true if cancelled, false otherwise.
     */
    public Boolean getCancelled() {
        return this.cancelled != null && this.cancelled;
    }

    public Boolean isCancelled() {
        return getCancelled();
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled != null ? cancelled : false;
    }

    public String getCancellationReason() {
        return this.cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    /**
     * Cancels the visit with the specified reason.
     * Validates that the visit is not already cancelled and the reason is non-empty.
     *
     * @param reason The cancellation explanation
     * @throws org.springframework.samples.petclinic.service.InvalidCancellationException if the visit is already cancelled or reason is blank
     */
    public void cancel(String reason) {
        // Reject repeated cancellation attempts to maintain consistent state
        if (Boolean.TRUE.equals(this.cancelled)) {
            throw new org.springframework.samples.petclinic.service.InvalidCancellationException("Visit is already cancelled");
        }
        // Ensure mandatory cancellation reason is provided
        if (reason == null || reason.trim().isEmpty()) {
            throw new org.springframework.samples.petclinic.service.InvalidCancellationException("Cancellation reason must not be empty");
        }
        this.cancelled = true;
        this.cancellationReason = reason.trim();
    }

}
